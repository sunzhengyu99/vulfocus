package com.sunzy.vulfocus.service;

import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.model.po.ContainerVul;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunzy
 * @since 2023-04-01
 */
public interface ContainerVulService extends IService<ContainerVul> {

    public Result getContainers(String flag, int page, String imageId);

    public Result checkFlag(String flag, String containerId);

    public Result startContainer(String containerId);

    public Result stopContainer(String containerId);

    public Result deleteContainer(String containerId);

}
