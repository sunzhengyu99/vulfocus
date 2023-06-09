package com.sunzy.vulfocus.service;

import com.github.dockerjava.api.model.Container;
import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.model.dto.UserDTO;
import com.sunzy.vulfocus.model.po.ContainerVul;
import com.sunzy.vulfocus.model.po.ImageInfo;
import com.sunzy.vulfocus.model.po.TaskInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.File;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunzy
 * @since 2023-04-01
 */
public interface TaskInfoService extends IService<TaskInfo> {

    /**
     * create pull image task
     * @param imageInfo
     * @param user
     * @param imageFile
     * @return
     */
    public String createImageTask(ImageInfo imageInfo, UserDTO user, File imageFile);

//    public String createRunContainerTask(ContainerVul containerVul, UserDTO user);

    public String createContainerTask(ContainerVul containerVul, UserDTO user) throws Exception;

    public String stopContainerTask(ContainerVul containerVul, UserDTO user) throws Exception;

    public String deleteContainerTask(ContainerVul containerVul, UserDTO user) throws Exception;

    public Result getTask(String taskId);

    public Result getBatchTask(String taskIds);

    Result dockerContainerRun(Container container, String command);
}
