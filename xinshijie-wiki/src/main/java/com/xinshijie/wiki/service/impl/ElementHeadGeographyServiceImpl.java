package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadGeography;
import com.xinshijie.wiki.mapper.ElementHeadGeographyMapper;
import com.xinshijie.wiki.service.IElementHeadGeographyService;
import com.xinshijie.wiki.dto.ElementHeadGeographyDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadGeographyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地理,元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadGeographyServiceImpl extends ServiceImpl<ElementHeadGeographyMapper, ElementHeadGeography> implements IElementHeadGeographyService {

    @Autowired
    private ElementHeadGeographyMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadGeographyVo> selectElementHeadGeographyList(ElementHeadGeographyDto dto) {
        return mapper.selectListElementHeadGeography(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadGeographyVo> selectPageElementHeadGeography(ElementHeadGeographyDto dto) {
        Page<ElementHeadGeographyVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadGeography(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadGeographyVo> getPageElementHeadGeography(ElementHeadGeographyDto dto) {
        Page<ElementHeadGeographyVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadGeographyVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadGeography(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadGeography add(ElementHeadGeographyDto dto) {
        ElementHeadGeography value = new ElementHeadGeography();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadGeographyDto dto) {
        return mapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return mapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementHeadGeographyVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadGeographyVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }

}
