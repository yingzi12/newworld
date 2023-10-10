package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Image;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.vo.ImageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图片信息表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface ImageMapper extends BaseMapper<Image> {

    /**
     * 查询图片信息表
     */
    List<ImageVo> selectImageList(ImageDto dto);

    /**
     * 普通方法
     * 分页查询图片信息表
     */
    Page<ImageVo> selectPageImage(Page<ImageVo> page, @Param("dto") ImageDto dto);

    /**
     * 分页查询图片信息表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ImageVo> getPageImage(Page<ImageVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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
