package com.sunzy.vulfocus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dockerjava.api.model.Network;
import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.common.SystemConstants;
import com.sunzy.vulfocus.model.dto.NetworkDTO;
import com.sunzy.vulfocus.model.dto.UserDTO;
import com.sunzy.vulfocus.model.po.LayoutServiceNetwork;
import com.sunzy.vulfocus.model.po.NetWorkInfo;
import com.sunzy.vulfocus.mapper.NetWorkInfoMapper;
import com.sunzy.vulfocus.service.LayoutServiceNetworkService;
import com.sunzy.vulfocus.service.NetWorkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunzy.vulfocus.utils.DockerTools;
import com.sunzy.vulfocus.utils.Utils;
import com.sunzy.vulfocus.utils.UserHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunzy
 * @since 2023-04-14
 */
@Service
@Transactional
public class NetWorkInfoServiceImpl extends ServiceImpl<NetWorkInfoMapper, NetWorkInfo> implements NetWorkInfoService {

    @Resource
    private LayoutServiceNetworkService layoutServiceNetworkService;
    @Override
    public Result createNetWorkInfo(NetworkDTO networkDTO) {
        UserDTO user = UserHolder.getUser();
        if(!user.getSuperuser()){
            return Result.fail("权限不足");
        }
        String netWorkName = networkDTO.getNetWorkName();
        if(StrUtil.isBlank(netWorkName)){
            return Result.fail("网卡名称不能为空！");
        }

        String netWorkSubnet = networkDTO.getNetWorkSubnet();
        if(netWorkSubnet == null){
            return Result.fail("子网不能为空！");
        }
        String netWorkGateway = networkDTO.getNetWorkGateway();
        if(StrUtil.isBlank(netWorkGateway)){
            return Result.fail("网关不能为空！");
        }
        String netWorkScope = networkDTO.getNetWorkScope();
        if(StrUtil.isBlank(netWorkScope)){
            netWorkScope = "local";
        }
        String netWorkDriver = networkDTO.getNetWorkDriver();
        if(StrUtil.isBlank(netWorkDriver)){
            netWorkDriver = "bridge";
        }
        Boolean enableIpv6 = networkDTO.getEnableIpv6();
        if(enableIpv6 == null){
            enableIpv6 = false;
        }
        Integer count = query().eq("net_work_name", netWorkName).count();
        if(count > 0){
            return Result.fail("网卡名称不能重复！");
        }

        count = query().eq("net_work_subnet", netWorkSubnet).count();
        if(count > 0){
            return Result.fail("子网不能重复！");
        }
        count = query().eq("net_work_gateway", netWorkGateway).count();
        if(count > 0){
            return Result.fail("网关不能重复！");
        }
        boolean isCreate = false;
        String netWorkClientId = "";
        if(!"".equals(netWorkSubnet)){
            List<Network> networkList = DockerTools.getNetworkList();
            for (Network network : networkList) {
                List<Network.Ipam.Config> config = network.getIpam().getConfig();
                if(config.size() > 0){
                    String subnet = config.get(0).getSubnet();
                    netWorkGateway = config.get(0).getGateway();
                    if(!netWorkSubnet.equals(subnet)){
                        continue;
                    }
                    netWorkClientId = network.getId();
                    netWorkScope = network.getScope();
                    netWorkDriver = network.getDriver();
                    enableIpv6 = network.getEnableIPv6();
                    isCreate = true;
                    break;
                }
            }
        }
        if(!isCreate){
            // 创建网卡
            try {
                Network network = DockerTools.createNetwork(networkDTO);
                netWorkClientId = network.getId();
                netWorkGateway = network.getIpam().getConfig().get(0).getGateway();
            } catch (Exception e){
                e.printStackTrace();
                return Result.fail("服务器内部错误");
            }
        }
        // 保存网卡信息到数据库
        NetWorkInfo netWorkInfo = new NetWorkInfo();
        netWorkInfo.setNetWorkId(Utils.getUUID());
        netWorkInfo.setNetWorkName(netWorkName);
        netWorkInfo.setNetWorkClientId(netWorkClientId);
        netWorkInfo.setNetWorkScope(netWorkScope);
        netWorkInfo.setNetWorkSubnet(netWorkSubnet);
        netWorkInfo.setNetWorkGateway(netWorkGateway);
        netWorkInfo.setNetWorkDriver(netWorkDriver);
        netWorkInfo.setCreateUser(user.getId());
        netWorkInfo.setEnableIpv6(enableIpv6);
        netWorkInfo.setCreateDate(LocalDateTime.now());
        netWorkInfo.setUpdateDate(LocalDateTime.now());
        save(netWorkInfo);
        return Result.ok(netWorkInfo);
    }

    @Override
    public Result removeNetWorkInfo(String networkId) {
        UserDTO user = UserHolder.getUser();
        if(!user.getSuperuser()){
            return Result.fail("权限不足");
        }
        NetWorkInfo netWorkInfo = getById(networkId);

        Integer count = layoutServiceNetworkService.query().eq("network_id", networkId).count();
        if(count > 0){
            return Result.fail("该网卡正在使用中");
        }
        try {
            Network network = DockerTools.getNetworkById(netWorkInfo.getNetWorkClientId());
            if(network == null){
                removeById(netWorkInfo);
                return Result.ok();
            }
            DockerTools.removeNetworkById(netWorkInfo.getNetWorkClientId());
        } catch (Exception e){
            List<Network> networkList = DockerTools.getNetworkList();
            String netWorkSubnet = netWorkInfo.getNetWorkSubnet();
            for (Network network : networkList) {
                List<Network.Ipam.Config> config = network.getIpam().getConfig();
                if(config.size() > 0){
                    String subnet = config.get(0).getSubnet();
                    if(!netWorkSubnet.equals(subnet)){
                        continue;
                    }
                    DockerTools.removeNetworkById(network.getId());
                    break;
                }
            }
        }
        removeById(networkId);
        return Result.ok();
    }

    @Override
    public Result getNetWorkInfoList(String data, int page) {

        UserDTO user = UserHolder.getUser();
        if(user.getSuperuser()){
            Page<NetWorkInfo> networkPage = new Page<>(page, SystemConstants.PAGE_SIZE);
            LambdaQueryWrapper<NetWorkInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(!StrUtil.isBlank(data), NetWorkInfo::getNetWorkName, data)
                    .or().like(!StrUtil.isBlank(data), NetWorkInfo::getNetWorkGateway, data)
                    .or().like(!StrUtil.isBlank(data), NetWorkInfo::getNetWorkSubnet, data);
            queryWrapper.orderByDesc(NetWorkInfo::getCreateDate);
            page(networkPage, queryWrapper);
            return Result.ok(networkPage);
        }
        return Result.ok();
    }


}
