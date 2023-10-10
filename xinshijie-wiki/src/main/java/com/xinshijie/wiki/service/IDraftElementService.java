package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftElement;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.vo.DraftElementVo;
import com.xinshijie.wiki.vo.SimpleElementVo;

import java.util.List;

/**
 * <p>
 * 元素草稿 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IDraftElementService extends IService<DraftElement> {

    /**
     * 查询元素草稿
     */
    List<DraftElementVo> selectDraftElementList(DraftElementFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素草稿
     */
    Page<DraftElementVo> selectPageDraftElement(DraftElementDto dto);

    /**
     * 分页查询元素草稿
     */
    Page<DraftElementVo> getPageDraftElement(DraftElementDto dto);

    /**
     * 新增数据
     */
    DraftElement add(ElementAddDto dto);

    /**
     * 根据id修改数据
     */
    DraftElement addEdit(ElementUpdateDto dto);

    int updateElementPageHtml(DraftElementUpdatePageHtmlDto dto);


    Integer edit(ElementUpdateDto dto);

    Boolean isEdit(Integer wid, Long deid);

    /**
     * 删除数据
     */
    Integer delById(Integer wid, Long deid);

    /**
     * 根据id数据
     */
    DraftElementVo getInfo(Integer wid, Long deid, Long userid, Integer isDel);

//    DraftElementVo selectDraft(Integer wid, Long deid,Long userid);

    int updateStatus(DraftElementUpdateDto element);

    int autorAuditYes(Integer wid, Long deid);

    Integer audit(AuditDto dto);

    SimpleElementVo issue(Integer wid, Long deid);

    int issueClose(Integer wid, Long deid);

    //自动把草稿状态改成取消
    int autorUpdateIss();

    //查询超时的审核
    List<DraftElementVo> findOvertimeAudit(Long size);

    //更新入库
    Long updateStorage(Integer wid, Long deid);
}
