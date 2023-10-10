package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.DraftContent;
import com.xinshijie.wiki.dto.DraftContentDto;
import com.xinshijie.wiki.mapper.DraftContentMapper;
import com.xinshijie.wiki.service.IDraftContentService;
import com.xinshijie.wiki.vo.DraftContentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素内容草稿 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class DraftContentServiceImpl extends ServiceImpl<DraftContentMapper, DraftContent> implements IDraftContentService {

    @Autowired
    private DraftContentMapper draftcontentMapper;


    /**
     * 查询元素内容草稿
     */
    @Override
    public List<DraftContentVo> selectDraftContentList(DraftContentDto dto) {
        return draftcontentMapper.selectDraftContentList(dto);
    }

    /**
     * 分页查询元素内容草稿
     */
    @Override
    public Page<DraftContentVo> selectPageDraftContent(DraftContentDto dto) {
        Page<DraftContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return draftcontentMapper.selectPageDraftContent(page, dto);
    }

    /**
     * 分页查询元素内容草稿
     */
    @Override
    public Page<DraftContentVo> getPageDraftContent(DraftContentDto dto) {
        Page<DraftContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DraftContentVo> qw = new QueryWrapper<>();
        return draftcontentMapper.getPageDraftContent(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftContent add(DraftContentDto dto) {
        DraftContent value = new DraftContent();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        draftcontentMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DraftContentDto dto) {
        return draftcontentMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return draftcontentMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public DraftContentVo getInfo(Long id) {
        return draftcontentMapper.getInfo(id);
    }

    @Override
    public Integer insetList(List<DraftContentDto> list) {
        return draftcontentMapper.insetList(list);
    }

    @Override
    public List<DraftContentVo> selectByWidEid(Integer wid, Long eid, Integer isDel) {
        return draftcontentMapper.selectByWidEid(wid, eid, isDel);
    }

    @Override
    public Integer updateByIdWidEid(DraftContentDto dto) {
        return draftcontentMapper.updateByIdWidEid(dto);
    }

    @Override
    public Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, List<Long> ids) {
        return draftcontentMapper.insetSelectContent(id, wid, eid, userId, userName, ids);
    }

    @Override
    public Integer deleteByEidWid(Long eid, Integer wid) {
        return draftcontentMapper.deleteByEidWid(eid, wid);
    }

}
