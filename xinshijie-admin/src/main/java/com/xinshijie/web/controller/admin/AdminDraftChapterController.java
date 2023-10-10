package com.xinshijie.web.controller.admin;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.DraftStatusEnums;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.DraftChapter;
import com.xinshijie.wiki.dto.ChapterAddDto;
import com.xinshijie.wiki.dto.ChapterAuditDto;
import com.xinshijie.wiki.dto.ChapterUpdateDto;
import com.xinshijie.wiki.dto.DraftChapterFindDto;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IDraftChapterService;
import com.xinshijie.wiki.vo.DraftChapterVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 元素草稿 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AdminDraftChapterController", description = "后台-章节草稿")
@RequestMapping("/admin/draftChapter")
public class AdminDraftChapterController extends BaseController {
    @Autowired
    private IDraftChapterService draftChapterService;
    @Autowired
    private IAuthorService authorService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询元素草稿", description = "查询元素草稿")
    public TableDataInfo<List<DraftChapterVo>> list(DraftChapterFindDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        dto.setCreateId(userid);
        startPage();
        List<DraftChapterVo> list = draftChapterService.selectDraftChapterList(dto);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    /**
     * 查询所有
     */
    @GetMapping("/listAdmin")
    @Operation(summary = "查询元素草稿", description = "查询元素草稿")
    public TableDataInfo<List<DraftChapterVo>> listAdmin(DraftChapterFindDto dto) {
        // 获取当前用户ID
        authorService.isCheck(dto.getSid());
        startPage();
        List<DraftChapterVo> list = draftChapterService.selectDraftChapterList(dto);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    /**
     * 查询所有
     */
    @GetMapping("/listAudit")
    @Operation(summary = "查询审核元素草稿", description = "查询审核元素草稿")
    public TableDataInfo<List<DraftChapterVo>> listAudit(DraftChapterFindDto dto) {
        authorService.isCheck(dto.getSid());
        if (dto.getAuditStatus() == null) {
            dto.setAuditStatus(0);
        }
        startPage();
        List<DraftChapterVo> list = draftChapterService.selectDraftChapterList(dto);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增元素草稿", description = "新增元素草稿")
    public Result<DraftChapter> add(@RequestBody ChapterAddDto dto) {
        dto.setLevel(1);
        dto.setPid(dto.getPid());
        dto.setStatus(DraftStatusEnums.DRAFT.getCode());
        dto.setCreateId(getUserId());
        dto.setCreateName(getUsername());
        dto.setCreateTime(LocalDateTime.now());
        return Result.success(draftChapterService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    @PutMapping("/edit")
    public Result<Integer> edit(@RequestBody ChapterUpdateDto dto) {
        return Result.success(draftChapterService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("sid") Long sid, @RequestParam("dscid") Long dscid) {
        return Result.success(draftChapterService.delById(sid, dscid));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DraftChapterVo> getInfo(@RequestParam("sid") Long sid, @RequestParam("dscid") Long dscid) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        return Result.success(draftChapterService.selectByIdSidUserId(sid, dscid, userid));
    }

    /**
     * 根据id获取详情
     * 1表示查看删除得，0表示不看删除得
     */
    @GetMapping("/getInfoAdmin")
    @Operation(summary = "getInfoAdmin", description = "根据id获取详情")
    public Result<DraftChapterVo> getInfoAdmin(@RequestParam("sid") Long sid, @RequestParam("dscid") Long dscid) {
        authorService.isCheck(sid);
        return Result.success(draftChapterService.selectByIdSidUserId(sid, dscid, null));
    }

    @GetMapping("/issue")
    @Operation(summary = "issue", description = "发布")
    public Result<Integer> issue(@RequestParam("sid") Long sid, @RequestParam("dscid") Long dscid) {
        return Result.success(draftChapterService.issue(sid, dscid));
    }

    @GetMapping("/issueClose")
    @Operation(summary = "issueClose", description = "发布")
    public Result<Integer> issueClose(@RequestParam("sid") Long sid, @RequestParam("dscid") Long dscid) {
        return Result.success(draftChapterService.issueClose(sid, dscid));
    }

    @PostMapping("/audit")
    @Operation(summary = "audit", description = "审核")
    public Result<Integer> audit(@RequestBody ChapterAuditDto dto) {
        return Result.success(draftChapterService.audit(dto));
    }
}

