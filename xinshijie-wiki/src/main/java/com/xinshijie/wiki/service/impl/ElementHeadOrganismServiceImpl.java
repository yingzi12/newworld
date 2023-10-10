package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadOrganism;
import com.xinshijie.wiki.mapper.ElementHeadOrganismMapper;
import com.xinshijie.wiki.service.IElementHeadOrganismService;
import com.xinshijie.wiki.dto.ElementHeadOrganismDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadOrganismVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 生物,元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadOrganismServiceImpl extends ServiceImpl<ElementHeadOrganismMapper, ElementHeadOrganism> implements IElementHeadOrganismService {

    @Autowired
    private ElementHeadOrganismMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadOrganismVo> selectElementHeadOrganismList(ElementHeadOrganismDto dto) {
        return mapper.selectListElementHeadOrganism(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadOrganismVo> selectPageElementHeadOrganism(ElementHeadOrganismDto dto) {
        Page<ElementHeadOrganismVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadOrganism(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadOrganismVo> getPageElementHeadOrganism(ElementHeadOrganismDto dto) {
        Page<ElementHeadOrganismVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadOrganismVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadOrganism(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadOrganism add(ElementHeadOrganismDto dto) {
        ElementHeadOrganism value = new ElementHeadOrganism();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadOrganismDto dto) {
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
    public ElementHeadOrganismVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadOrganismVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }

}
