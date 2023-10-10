package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftHeadThing;
import com.xinshijie.wiki.mapper.DraftHeadThingMapper;
import com.xinshijie.wiki.service.IDraftHeadThingService;
import com.xinshijie.wiki.dto.DraftHeadThingDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadThingVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
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
public class DraftHeadThingServiceImpl extends ServiceImpl<DraftHeadThingMapper, DraftHeadThing> implements IDraftHeadThingService {

    @Autowired
    private DraftHeadThingMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<DraftHeadThingVo> selectDraftHeadThingList(DraftHeadThingDto dto) {
        return mapper.selectListDraftHeadThing(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadThingVo> selectPageDraftHeadThing(DraftHeadThingDto dto) {
        Page<DraftHeadThingVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageDraftHeadThing(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadThingVo> getPageDraftHeadThing(DraftHeadThingDto dto) {
        Page<DraftHeadThingVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<DraftHeadThingVo> qw = new QueryWrapper<>();
        return mapper.getPageDraftHeadThing(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftHeadThing add(DraftHeadThingDto dto) {
        DraftHeadThing value = new DraftHeadThing();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftHeadThingDto dto) {
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
    public DraftHeadThingVo getInfo(Long id) {
        return mapper.getInfo(id);
    }
    @Override
    public DraftHeadThingVo selectByWidEid(Integer wid, Long eid, Integer isDel) {
        return mapper.selectByWidEid(wid, eid, isDel);
    }
    @Override
    public Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, Long ehid) {
        return mapper.insetSelectContent(id, wid, eid, userId, userName, ehid);
    }

    @Override
    public Integer deleteByEidWid(Long eid, Integer wid) {
        return mapper.deleteByEidWid(eid, wid);
    }
}
