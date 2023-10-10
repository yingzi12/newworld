package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementCategory;
import com.xinshijie.wiki.dto.ElementCategoryDto;
import com.xinshijie.wiki.dto.ElementCategoryFindDto;
import com.xinshijie.wiki.vo.CategoryVo;
import com.xinshijie.wiki.vo.ElementCategoryVo;

import java.util.List;

/**
 * <p>
 * 元素分类对应表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IElementCategoryService extends IService<ElementCategory> {

    /**
     * 查询元素分类对应表
     */
    List<ElementCategoryVo> selectElementCategoryList(ElementCategoryFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素分类对应表
     */
    Page<ElementCategoryVo> selectPageElementCategory(ElementCategoryFindDto dto);

    /**
     * 分页查询元素分类对应表
     */
    Page<ElementCategoryVo> getPageElementCategory(ElementCategoryFindDto dto);

    /**
     * 新增数据
     */
    ElementCategory add(ElementCategoryDto dto);

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

    int insetList(List<ElementCategoryDto> list);

    List<CategoryVo> selectCategoryLab(Integer wid, Long eid);

    int insertSelectEid(Integer wid, Long deid, Long eid);

    int insertSelect(Integer wid, Long deid);


}
