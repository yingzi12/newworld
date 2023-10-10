package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Category;
import com.xinshijie.wiki.dto.CategoryDto;
import com.xinshijie.wiki.dto.CategoryFindDto;
import com.xinshijie.wiki.vo.CategorySimplyVo;
import com.xinshijie.wiki.vo.CategoryTreeVo;
import com.xinshijie.wiki.vo.CategoryVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 元素分类 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 查询元素分类
     */
    List<CategoryVo> selectCategoryList(CategoryFindDto dto);

    List<CategorySimplyVo> selectSinplyCategoryList(Integer wid);

    /**
     * 分页查询。普通方法
     * 查询元素分类
     */
    Page<CategoryVo> selectPageCategory(CategoryFindDto dto);

    /**
     * 分页查询元素分类
     */
    Page<CategoryVo> getPageCategory(CategoryFindDto dto);

    /**
     * 新增数据
     */
    Category add(CategoryDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(CategoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    CategoryVo getInfo(Long id);

    List<CategoryTreeVo> selectCategoryByWid(Integer wid);

    List<CategoryTreeVo> selectByWidTier(Integer wid, Integer tier);

    Category insertCategory(Category category);

    Long countCategory(Integer wid);

    /**
     * 返回上级与当前类型的label
     *
     * @param ids
     * @return
     */
    List<String> findAllNameList(List<Long> ids);

    List<String> findNameAllByIds(Set<Long> ids);

    List<String> findPidpathByIds(List<Long> ids);
}
