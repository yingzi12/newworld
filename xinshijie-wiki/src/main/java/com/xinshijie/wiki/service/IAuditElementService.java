package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.AuditElement;
import com.xinshijie.wiki.dto.AuditElementDto;
import com.xinshijie.wiki.vo.AuditElementVo;

import java.util.List;

/**
 * <p>
 * 元素审核 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IAuditElementService extends IService<AuditElement> {

    /**
     * 查询元素审核
     */
    List<AuditElementVo> selectAuditElementList(AuditElementDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素审核
     */
    Page<AuditElementVo> selectPageAuditElement(AuditElementDto dto);

    /**
     * 分页查询元素审核
     */
    Page<AuditElementVo> getPageAuditElement(AuditElementDto dto);

    /**
     * 新增数据
     */
    Integer add(AuditElementDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(AuditElementDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    AuditElementVo getInfo(Long id);
}
