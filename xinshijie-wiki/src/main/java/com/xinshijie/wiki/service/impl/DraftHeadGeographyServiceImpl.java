package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftHeadGeography;
import com.xinshijie.wiki.mapper.DraftHeadGeographyMapper;
import com.xinshijie.wiki.service.IDraftHeadGeographyService;
import com.xinshijie.wiki.dto.DraftHeadGeographyDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadGeographyVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
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
public class DraftHeadGeographyServiceImpl extends ServiceImpl<DraftHeadGeographyMapper, DraftHeadGeography> implements IDraftHeadGeographyService {

    @Autowired
    private DraftHeadGeographyMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<DraftHeadGeographyVo> selectDraftHeadGeographyList(DraftHeadGeographyDto dto) {
        return mapper.selectListDraftHeadGeography(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadGeographyVo> selectPageDraftHeadGeography(DraftHeadGeographyDto dto) {
        Page<DraftHeadGeographyVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageDraftHeadGeography(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadGeographyVo> getPageDraftHeadGeography(DraftHeadGeographyDto dto) {
        Page<DraftHeadGeographyVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<DraftHeadGeographyVo> qw = new QueryWrapper<>();
        return mapper.getPageDraftHeadGeography(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftHeadGeography add(DraftHeadGeographyDto dto) {
        DraftHeadGeography value = new DraftHeadGeography();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftHeadGeographyDto dto) {
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
    public DraftHeadGeographyVo getInfo(Long id) {
        return mapper.getInfo(id);
    }
    @Override
    public DraftHeadGeographyVo selectByWidEid(Integer wid, Long eid, Integer isDel) {
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
