package com.sunzy.vulfocus.controller;


import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.model.dto.UserDTO;
import com.sunzy.vulfocus.model.po.ContainerVul;
import com.sunzy.vulfocus.service.ContainerVulService;
import com.sunzy.vulfocus.service.TaskInfoService;
import com.sunzy.vulfocus.service.impl.TaskInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunzy
 * @since 2023-04-01
 */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskInfoController {

    @Resource
    private TaskInfoServiceImpl taskService;

    @Resource
    private ContainerVulService containerService;
    @GetMapping("/{taskId}/get/")
    public Result getTaskInfo(@PathVariable("taskId") String taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping("/batch/batch/")
    public Result batchTask(@RequestBody String taskIds){
        return taskService.getBatchTask(taskIds);
    }


}
