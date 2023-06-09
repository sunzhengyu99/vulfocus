package com.sunzy.vulfocus.service;

import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.model.dto.CreateImage;
import com.sunzy.vulfocus.model.dto.ImageDTO;
import com.sunzy.vulfocus.model.dto.UserDTO;
import com.sunzy.vulfocus.model.po.ImageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunzy
 * @since 2023-04-01
 */
public interface ImageInfoService extends IService<ImageInfo> {
    public ImageDTO handlerImageDTO(ImageInfo imageInfo, UserDTO user) throws Exception;

    public Result getLocalImages();

    public Result getImageList(String query, int page, String flag) throws Exception;

    public Result createImage(CreateImage createImage);

    public Result editImage(ImageDTO imageDTO);

    public Result downloadImage(String imageId);

    public Result deleteImage(String imageId) throws Exception;

    public Result batchLocalAdd(String imageNamesStr);

    public Result startContainer(String imageId);
}
