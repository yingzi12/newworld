package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Category;
import com.xinshijie.wiki.dto.CategoryDto;
import com.xinshijie.wiki.dto.CategoryFindDto;
import com.xinshijie.wiki.vo.CategorySimplyVo;
import com.xinshijie.wiki.vo.CategoryTreeVo;
import com.xinshijie.wiki.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 元素分类 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询元素分类
     */
    List<CategoryVo> selectCategoryList(CategoryFindDto dto);

    List<CategorySimplyVo> selectSinplyCategoryList(@Param("wid") Integer wid);

    /**
     * 普通方法
     * 分页查询元素分类
     */
    Page<CategoryVo> selectPageCategory(Page<CategoryVo> page, @Param("dto") CategoryFindDto dto);

    /**
     * 分页查询元素分类
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<CategoryVo> getPageCategory(Page<CategoryVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);


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


    List<CategoryTreeVo> selectByWid(@Param("wid") Integer wid);

    List<CategoryTreeVo> selectByWidTier(@Param("wid") Integer wid, @Param("tier") Integer tier);


    List<CategoryVo> selectByWidLabel(@Param("wid") Integer wid, @Param("pid") Long pid, @Param("label") String label);

    Integer countByWidLabel(@Param("wid") Integer wid, @Param("pid") Long pid, @Param("label") String label);

    List<String> findNameAllByIds(@Param("ids") Set<Long> ids);

    List<String> findPidpathByIds(@Param("ids") List<Long> ids);

}
