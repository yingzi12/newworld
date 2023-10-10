package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadThing;
import com.xinshijie.wiki.mapper.ElementHeadThingMapper;
import com.xinshijie.wiki.service.IElementHeadThingService;
import com.xinshijie.wiki.dto.ElementHeadThingDto;
import com.xinshijie.wiki.vo.ElementHeadRaceVo;
import com.xinshijie.wiki.vo.ElementHeadThingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物品/材料,元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadThingServiceImpl extends ServiceImpl<ElementHeadThingMapper, ElementHeadThing> implements IElementHeadThingService {

    @Autowired
    private ElementHeadThingMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadThingVo> selectElementHeadThingList(ElementHeadThingDto dto) {
        return mapper.selectListElementHeadThing(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadThingVo> selectPageElementHeadThing(ElementHeadThingDto dto) {
        Page<ElementHeadThingVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadThing(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadThingVo> getPageElementHeadThing(ElementHeadThingDto dto) {
        Page<ElementHeadThingVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadThingVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadThing(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadThing add(ElementHeadThingDto dto) {
        ElementHeadThing value = new ElementHeadThing();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadThingDto dto) {
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
    public ElementHeadThingVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadThingVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }
}
