package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementContent;
import com.xinshijie.wiki.dto.ElementContentDto;
import com.xinshijie.wiki.dto.ElementContentFindDto;
import com.xinshijie.wiki.mapper.ElementContentMapper;
import com.xinshijie.wiki.service.IElementContentService;
import com.xinshijie.wiki.vo.ElementContentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素内容 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class ElementContentServiceImpl extends ServiceImpl<ElementContentMapper, ElementContent> implements IElementContentService {

    @Autowired
    private ElementContentMapper elementcontentMapper;

    /**
     * 查询元素内容
     */
    @Override
    public List<ElementContentVo> selectElementContentList(ElementContentFindDto dto) {
        return elementcontentMapper.selectElementContentList(dto);
    }

    /**
     * 分页查询元素内容
     */
    @Override
    public Page<ElementContentVo> selectPageElementContent(ElementContentFindDto dto) {
        Page<ElementContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return elementcontentMapper.selectPageElementContent(page, dto);
    }

    /**
     * 分页查询元素内容
     */
    @Override
    public Page<ElementContentVo> getPageElementContent(ElementContentFindDto dto) {
        Page<ElementContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementContentVo> qw = new QueryWrapper<>();
        return elementcontentMapper.getPageElementContent(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementContent add(ElementContentDto dto) {
        ElementContent value = new ElementContent();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        elementcontentMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementContentDto dto) {
        return elementcontentMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return elementcontentMapper.delById(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return elementcontentMapper.delByIdWidEid(id, wid, eid);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementContentVo getInfo(Long id) {
        return elementcontentMapper.getInfo(id);
    }

    @Override
    public int insetList(List<ElementContentDto> list) {
        return elementcontentMapper.insetList(list);
    }

    @Override
    public List<ElementContentVo> selectByWidEid(Integer wid, Long eid) {
        return elementcontentMapper.selectByWidEid(wid, eid);
    }

    @Override
    public Integer updateByIdWidEid(ElementContentDto dto) {
        return elementcontentMapper.updateByIdWidEid(dto);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return elementcontentMapper.insertSelect(wid, deid);
    }

    @Override
    public int insertSelectEid(Integer wid, Long deid, Long eid) {
        return elementcontentMapper.insertSelectEid(wid, deid, eid);
    }

}
