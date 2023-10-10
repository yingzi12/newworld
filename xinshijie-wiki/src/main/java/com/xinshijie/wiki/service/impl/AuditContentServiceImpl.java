package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.AuditContent;
import com.xinshijie.wiki.dto.AuditContentDto;
import com.xinshijie.wiki.mapper.AuditContentMapper;
import com.xinshijie.wiki.service.IAuditContentService;
import com.xinshijie.wiki.vo.AuditContentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素内容审核 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class AuditContentServiceImpl extends ServiceImpl<AuditContentMapper, AuditContent> implements IAuditContentService {

    @Autowired
    private AuditContentMapper auditcontentMapper;

    /**
     * 查询元素内容审核
     */
    @Override
    public List<AuditContentVo> selectAuditContentList(AuditContentDto dto) {
        return auditcontentMapper.selectAuditContentList(dto);
    }

    /**
     * 分页查询元素内容审核
     */
    @Override
    public Page<AuditContentVo> selectPageAuditContent(AuditContentDto dto) {
        Page<AuditContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return auditcontentMapper.selectPageAuditContent(page, dto);
    }

    /**
     * 分页查询元素内容审核
     */
    @Override
    public Page<AuditContentVo> getPageAuditContent(AuditContentDto dto) {
        Page<AuditContentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<AuditContentVo> qw = new QueryWrapper<>();
        return auditcontentMapper.getPageAuditContent(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Integer add(AuditContentDto dto) {
        AuditContent value = new AuditContent();
        BeanUtils.copyProperties(dto, value);
        return auditcontentMapper.insert(value);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(AuditContentDto dto) {
        return auditcontentMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return auditcontentMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public AuditContentVo getInfo(Long id) {
        return auditcontentMapper.getInfo(id);
    }
}
