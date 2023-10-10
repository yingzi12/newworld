package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftHeadRace;
import com.xinshijie.wiki.mapper.DraftHeadRaceMapper;
import com.xinshijie.wiki.service.IDraftHeadRaceService;
import com.xinshijie.wiki.dto.DraftHeadRaceDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadRaceVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
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
public class DraftHeadRaceServiceImpl extends ServiceImpl<DraftHeadRaceMapper, DraftHeadRace> implements IDraftHeadRaceService {

    @Autowired
    private DraftHeadRaceMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<DraftHeadRaceVo> selectDraftHeadRaceList(DraftHeadRaceDto dto) {
        return mapper.selectListDraftHeadRace(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadRaceVo> selectPageDraftHeadRace(DraftHeadRaceDto dto) {
        Page<DraftHeadRaceVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageDraftHeadRace(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadRaceVo> getPageDraftHeadRace(DraftHeadRaceDto dto) {
        Page<DraftHeadRaceVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<DraftHeadRaceVo> qw = new QueryWrapper<>();
        return mapper.getPageDraftHeadRace(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftHeadRace add(DraftHeadRaceDto dto) {
        DraftHeadRace value = new DraftHeadRace();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftHeadRaceDto dto) {
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
    public DraftHeadRaceVo getInfo(Long id) {
        return mapper.getInfo(id);
    }
    @Override
    public DraftHeadRaceVo selectByWidEid(Integer wid, Long eid, Integer isDel) {
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
