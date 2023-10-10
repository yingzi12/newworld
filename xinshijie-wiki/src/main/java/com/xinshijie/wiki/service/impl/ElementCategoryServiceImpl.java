package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementCategory;
import com.xinshijie.wiki.dto.ElementCategoryDto;
import com.xinshijie.wiki.dto.ElementCategoryFindDto;
import com.xinshijie.wiki.mapper.ElementCategoryMapper;
import com.xinshijie.wiki.service.IElementCategoryService;
import com.xinshijie.wiki.vo.CategoryVo;
import com.xinshijie.wiki.vo.ElementCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素分类对应表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class ElementCategoryServiceImpl extends ServiceImpl<ElementCategoryMapper, ElementCategory> implements IElementCategoryService {

    @Autowired
    private ElementCategoryMapper elementcategoryMapper;

    /**
     * 查询元素分类对应表
     */
    @Override
    public List<ElementCategoryVo> selectElementCategoryList(ElementCategoryFindDto dto) {
        return elementcategoryMapper.selectElementCategoryList(dto);
    }

    /**
     * 分页查询元素分类对应表
     */
    @Override
    public Page<ElementCategoryVo> selectPageElementCategory(ElementCategoryFindDto dto) {
        Page<ElementCategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return elementcategoryMapper.selectPageElementCategory(page, dto);
    }

    /**
     * 分页查询元素分类对应表
     */
    @Override
    public Page<ElementCategoryVo> getPageElementCategory(ElementCategoryFindDto dto) {
        Page<ElementCategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementCategoryVo> qw = new QueryWrapper<>();
        return elementcategoryMapper.getPageElementCategory(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementCategory add(ElementCategoryDto dto) {

        ElementCategory value = new ElementCategory();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        elementcategoryMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementCategoryDto dto) {
        return elementcategoryMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return elementcategoryMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementCategoryVo getInfo(Long id) {
        return elementcategoryMapper.getInfo(id);
    }

    @Override
    public int insetList(List<ElementCategoryDto> list) {
        return elementcategoryMapper.insetList(list);
    }

    @Override
    public List<CategoryVo> selectCategoryLab(Integer wid, Long eid) {
        return elementcategoryMapper.selectCategoryLab(wid, eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return elementcategoryMapper.insertSelect(wid, deid);
    }

    @Override
    public int insertSelectEid(Integer wid, Long deid, Long eid) {
        return elementcategoryMapper.insertSelectEid(wid, deid, eid);
    }
}
