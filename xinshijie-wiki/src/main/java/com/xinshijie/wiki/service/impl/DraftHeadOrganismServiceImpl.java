package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftHeadOrganism;
import com.xinshijie.wiki.mapper.DraftHeadOrganismMapper;
import com.xinshijie.wiki.service.IDraftHeadOrganismService;
import com.xinshijie.wiki.dto.DraftHeadOrganismDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadOrganismVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
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
public class DraftHeadOrganismServiceImpl extends ServiceImpl<DraftHeadOrganismMapper, DraftHeadOrganism> implements IDraftHeadOrganismService {

    @Autowired
    private DraftHeadOrganismMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<DraftHeadOrganismVo> selectDraftHeadOrganismList(DraftHeadOrganismDto dto) {
        return mapper.selectListDraftHeadOrganism(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadOrganismVo> selectPageDraftHeadOrganism(DraftHeadOrganismDto dto) {
        Page<DraftHeadOrganismVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageDraftHeadOrganism(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<DraftHeadOrganismVo> getPageDraftHeadOrganism(DraftHeadOrganismDto dto) {
        Page<DraftHeadOrganismVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<DraftHeadOrganismVo> qw = new QueryWrapper<>();
        return mapper.getPageDraftHeadOrganism(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftHeadOrganism add(DraftHeadOrganismDto dto) {
        DraftHeadOrganism value = new DraftHeadOrganism();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftHeadOrganismDto dto) {
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
    public DraftHeadOrganismVo getInfo(Long id) {
        return mapper.getInfo(id);
    }
    @Override
    public DraftHeadOrganismVo selectByWidEid(Integer wid, Long eid, Integer isDel) {
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
