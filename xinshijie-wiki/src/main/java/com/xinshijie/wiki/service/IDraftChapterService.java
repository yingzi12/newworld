package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftChapter;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.vo.DraftChapterVo;

import java.util.List;

/**
 * <p>
 * 故事章节 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface IDraftChapterService extends IService<DraftChapter> {

    /**
     * 查询故事章节
     */
    List<DraftChapterVo> selectDraftChapterList(DraftChapterFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询故事章节
     */
    Page<DraftChapterVo> selectPageDraftChapter(DraftChapterDto dto);

    /**
     * 分页查询故事章节
     */
    Page<DraftChapterVo> getPageDraftChapter(DraftChapterDto dto);

    /**
     * 新增数据
     */
    DraftChapter add(ChapterAddDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ChapterUpdateDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long sid, Long dscid);

    /**
     * 根据id数据
     */
    DraftChapterVo selectByIdSidUserId(Long sid, Long dscid, Long userId);
    DraftChapterVo getInfo(Long dscid);

    int audit(ChapterAuditDto dto);

    Integer issue(Long sid, Long dscid);

    int issueClose(Long sid, Long dscid);

    //自动取消发布
    int autorIssNo(Long sid, Long dscid);

    int autorAuditYes(Long sid, Long dscid);

    int updateStatus(DraftChapterDto dto);

    //自动把草稿状态改成取消
    int autorUpdateIss();

    //查询超时的审核
    List<DraftChapterVo> findOvertimeAudit(Long size);

    //更新入库
    void updateStorage(Long sid, Long dscid);
}
