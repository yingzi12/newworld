package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.Image;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.mapper.ImageMapper;
import com.xinshijie.wiki.service.IImageService;
import com.xinshijie.wiki.vo.ImageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图片信息表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ImageVo> selectImageList(ImageDto dto) {
        return imageMapper.selectImageList(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ImageVo> selectPageImage(ImageDto dto) {
        Page<ImageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return imageMapper.selectPageImage(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ImageVo> getPageImage(ImageDto dto) {
        Page<ImageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ImageVo> qw = new QueryWrapper<>();
        return imageMapper.getPageImage(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Image add(ImageDto dto) {
        Image value = new Image();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        imageMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ImageDto dto) {
        return imageMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return imageMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ImageVo getInfo(Long id) {
        return imageMapper.getInfo(id);
    }

    @Override
    public ImageVo getInfoByMd5(String md5) {
        return imageMapper.getInfoByMd5(md5);
    }
}
