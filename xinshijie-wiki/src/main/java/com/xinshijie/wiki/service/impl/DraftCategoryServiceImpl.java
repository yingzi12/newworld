package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftCategory;
import com.xinshijie.wiki.dto.DraftCategoryDto;
import com.xinshijie.wiki.dto.DraftCategoryFindDto;
import com.xinshijie.wiki.mapper.DraftCategoryMapper;
import com.xinshijie.wiki.service.IDraftCategoryService;
import com.xinshijie.wiki.service.IOperationCountService;
import com.xinshijie.wiki.vo.CategoryVo;
import com.xinshijie.wiki.vo.DraftCategoryVo;
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
public class DraftCategoryServiceImpl extends ServiceImpl<DraftCategoryMapper, DraftCategory> implements IDraftCategoryService {

    @Autowired
    private DraftCategoryMapper draftCategoryMapper;

    @Autowired
    private IOperationCountService countService;

    /**
     * 查询元素分类对应表
     */
    @Override
    public List<DraftCategoryVo> selectDraftCategoryList(DraftCategoryFindDto dto) {
        return draftCategoryMapper.selectDraftCategoryList(dto);
    }

    /**
     * 分页查询元素分类对应表
     */
    @Override
    public Page<DraftCategoryVo> selectPageDraftCategory(DraftCategoryFindDto dto) {
        Page<DraftCategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return draftCategoryMapper.selectPageDraftCategory(page, dto);
    }

    /**
     * 分页查询元素分类对应表
     */
    @Override
    public Page<DraftCategoryVo> getPageDraftCategory(DraftCategoryFindDto dto) {
        Page<DraftCategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DraftCategoryVo> qw = new QueryWrapper<>();
        return draftCategoryMapper.getPageDraftCategory(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftCategory add(DraftCategoryDto dto) {

        DraftCategory value = new DraftCategory();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        draftCategoryMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftCategoryDto dto) {
        return draftCategoryMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return draftCategoryMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public DraftCategoryVo getInfo(Long id) {
        return draftCategoryMapper.getInfo(id);
    }

    @Override
    public int insetList(List<DraftCategoryDto> list) {
        return draftCategoryMapper.insetList(list);
    }

    @Override
    public List<CategoryVo> selectCategoryLab(Integer wid, Long eid) {
        return draftCategoryMapper.selectCategoryLab(wid, eid);
    }

    @Override
    public Integer deleteByEidWid(Long eid, Integer wid) {
        return draftCategoryMapper.deleteByEidWid(eid, wid);
    }


}
