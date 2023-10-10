package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadRace;
import com.xinshijie.wiki.mapper.ElementHeadRaceMapper;
import com.xinshijie.wiki.service.IElementHeadRaceService;
import com.xinshijie.wiki.dto.ElementHeadRaceDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadRaceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 种族,元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadRaceServiceImpl extends ServiceImpl<ElementHeadRaceMapper, ElementHeadRace> implements IElementHeadRaceService {

    @Autowired
    private ElementHeadRaceMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadRaceVo> selectElementHeadRaceList(ElementHeadRaceDto dto) {
        return mapper.selectListElementHeadRace(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadRaceVo> selectPageElementHeadRace(ElementHeadRaceDto dto) {
        Page<ElementHeadRaceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadRace(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadRaceVo> getPageElementHeadRace(ElementHeadRaceDto dto) {
        Page<ElementHeadRaceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadRaceVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadRace(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadRace add(ElementHeadRaceDto dto) {
        ElementHeadRace value = new ElementHeadRace();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadRaceDto dto) {
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
    public ElementHeadRaceVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadRaceVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }

}
