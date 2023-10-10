package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftCategory;
import com.xinshijie.wiki.dto.DraftCategoryDto;
import com.xinshijie.wiki.dto.DraftCategoryFindDto;
import com.xinshijie.wiki.vo.CategoryVo;
import com.xinshijie.wiki.vo.DraftCategoryVo;

import java.util.List;

/**
 * <p>
 * 元素分类对应表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IDraftCategoryService extends IService<DraftCategory> {

    /**
     * 查询元素分类对应表
     */
    List<DraftCategoryVo> selectDraftCategoryList(DraftCategoryFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素分类对应表
     */
    Page<DraftCategoryVo> selectPageDraftCategory(DraftCategoryFindDto dto);

    /**
     * 分页查询元素分类对应表
     */
    Page<DraftCategoryVo> getPageDraftCategory(DraftCategoryFindDto dto);

    /**
     * 新增数据
     */
    DraftCategory add(DraftCategoryDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftCategoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftCategoryVo getInfo(Long id);

    int insetList(List<DraftCategoryDto> list);

    List<CategoryVo> selectCategoryLab(Integer wid, Long eid);

    Integer deleteByEidWid(Long eid, Integer wid);
}
