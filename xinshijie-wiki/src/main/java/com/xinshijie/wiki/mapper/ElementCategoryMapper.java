package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.ElementCategory;
import com.xinshijie.wiki.dto.ElementCategoryDto;
import com.xinshijie.wiki.dto.ElementCategoryFindDto;
import com.xinshijie.wiki.vo.CategoryVo;
import com.xinshijie.wiki.vo.ElementCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素分类对应表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface ElementCategoryMapper extends BaseMapper<ElementCategory> {

    /**
     * 查询元素分类对应表
     */
    List<ElementCategoryVo> selectElementCategoryList(ElementCategoryFindDto dto);

    /**
     * 普通方法
     * 分页查询元素分类对应表
     */
    Page<ElementCategoryVo> selectPageElementCategory(Page<ElementCategoryVo> page, @Param("dto") ElementCategoryFindDto dto);

    /**
     * 分页查询元素分类对应表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ElementCategoryVo> getPageElementCategory(Page<ElementCategoryVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementCategoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementCategoryVo getInfo(Long id);

    int insetList(@Param("list") List<ElementCategoryDto> list);

    List<CategoryVo> selectCategoryLab(@Param("wid") Integer wid, @Param("eid") Long eid);

    int insertSelectEid(@Param("wid") Integer wid, @Param("deid") Long deid, @Param("eid") Long eid);

    int insertSelect(@Param("wid") Integer wid, @Param("deid") Long deid);


}
