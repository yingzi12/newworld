package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Image;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.vo.ImageVo;

import java.util.List;

/**
 * <p>
 * 图片信息表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface IImageService extends IService<Image> {

    /**
     * 查询图片信息表
     */
    List<ImageVo> selectImageList(ImageDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ImageVo> selectPageImage(ImageDto dto);

    /**
     * 分页查询图片信息表
     */
    Page<ImageVo> getPageImage(ImageDto dto);

    /**
     * 新增数据
     */
    Image add(ImageDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ImageDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ImageVo getInfo(Long id);

    ImageVo getInfoByMd5(String md5);
}
