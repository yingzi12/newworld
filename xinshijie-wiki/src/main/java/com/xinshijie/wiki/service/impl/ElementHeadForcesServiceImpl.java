package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadForces;
import com.xinshijie.wiki.mapper.ElementHeadForcesMapper;
import com.xinshijie.wiki.service.IElementHeadForcesService;
import com.xinshijie.wiki.dto.ElementHeadForcesDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 势力.元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadForcesServiceImpl extends ServiceImpl<ElementHeadForcesMapper, ElementHeadForces> implements IElementHeadForcesService {

    @Autowired
    private ElementHeadForcesMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadForcesVo> selectElementHeadForcesList(ElementHeadForcesDto dto) {
        return mapper.selectListElementHeadForces(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadForcesVo> selectPageElementHeadForces(ElementHeadForcesDto dto) {
        Page<ElementHeadForcesVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadForces(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadForcesVo> getPageElementHeadForces(ElementHeadForcesDto dto) {
        Page<ElementHeadForcesVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadForcesVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadForces(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadForces add(ElementHeadForcesDto dto) {
        ElementHeadForces value = new ElementHeadForces();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadForcesDto dto) {
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
    public ElementHeadForcesVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadForcesVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }

}
