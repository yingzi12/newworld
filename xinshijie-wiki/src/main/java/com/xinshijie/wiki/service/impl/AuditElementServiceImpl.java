package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.AuditElement;
import com.xinshijie.wiki.dto.AuditElementDto;
import com.xinshijie.wiki.mapper.AuditElementMapper;
import com.xinshijie.wiki.service.IAuditElementService;
import com.xinshijie.wiki.vo.AuditElementVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素审核 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class AuditElementServiceImpl extends ServiceImpl<AuditElementMapper, AuditElement> implements IAuditElementService {

    @Autowired
    private AuditElementMapper auditelementMapper;

    /**
     * 查询元素审核
     */
    @Override
    public List<AuditElementVo> selectAuditElementList(AuditElementDto dto) {
        return auditelementMapper.selectAuditElementList(dto);
    }

    /**
     * 分页查询元素审核
     */
    @Override
    public Page<AuditElementVo> selectPageAuditElement(AuditElementDto dto) {
        Page<AuditElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return auditelementMapper.selectPageAuditElement(page, dto);
    }

    /**
     * 分页查询元素审核
     */
    @Override
    public Page<AuditElementVo> getPageAuditElement(AuditElementDto dto) {
        Page<AuditElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<AuditElementVo> qw = new QueryWrapper<>();
        return auditelementMapper.getPageAuditElement(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Integer add(AuditElementDto dto) {
        AuditElement value = new AuditElement();
        BeanUtils.copyProperties(dto, value);
        return auditelementMapper.insert(value);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(AuditElementDto dto) {
        return auditelementMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return auditelementMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public AuditElementVo getInfo(Long id) {
        return auditelementMapper.getInfo(id);
    }
}
