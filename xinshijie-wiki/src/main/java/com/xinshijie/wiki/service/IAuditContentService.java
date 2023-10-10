package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.AuditContent;
import com.xinshijie.wiki.dto.AuditContentDto;
import com.xinshijie.wiki.vo.AuditContentVo;

import java.util.List;

/**
 * <p>
 * 元素内容审核 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IAuditContentService extends IService<AuditContent> {

    /**
     * 查询元素内容审核
     */
    List<AuditContentVo> selectAuditContentList(AuditContentDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素内容审核
     */
    Page<AuditContentVo> selectPageAuditContent(AuditContentDto dto);

    /**
     * 分页查询元素内容审核
     */
    Page<AuditContentVo> getPageAuditContent(AuditContentDto dto);

    /**
     * 新增数据
     */
    Integer add(AuditContentDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(AuditContentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    AuditContentVo getInfo(Long id);
}
