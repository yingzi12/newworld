package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.DraftElement;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.service.IDraftElementService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.vo.DraftElementVo;
import com.xinshijie.wiki.vo.SimpleElementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "AdminDraftElementController", description = "后台-元素草稿")
@RequestMapping("/admin/draftElement")
public class AdminDraftElementController extends BaseController {
    @Autowired
    private IDraftElementService draftelementService;
    @Autowired
    private IManageService manageService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询元素草稿", description = "查询元素草稿")
    public TableDataInfo<List<DraftElementVo>> list(DraftElementFindDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        dto.setCreateId(userid);
        startPage();
        List<DraftElementVo> list = draftelementService.selectDraftElementList(dto);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    /**
     * 查询所有
     */
    @GetMapping("/listAudit")
    @Operation(summary = "查询审核元素草稿", description = "查询审核元素草稿")
    public TableDataInfo<List<DraftElementVo>> listAudit(DraftElementFindDto dto) {
        manageService.isCheck(dto.getWid());
        if (dto.getAuditStatus() == null) {
            dto.setAuditStatus(0);
        }
        startPage();
        List<DraftElementVo> list = draftelementService.selectDraftElementList(dto);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增元素草稿", description = "新增元素草稿")
    public Result<DraftElement> add(@RequestBody ElementAddDto dto) {
        return Result.success(draftelementService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    @PutMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementUpdateDto dto) {
        return Result.success(draftelementService.edit(dto));
    }

    @Log(title = "元素", businessType = BusinessType.UPDATE)
    @Operation(summary = "保存元素草稿", description = "保存元素草稿")
    @PutMapping("/editPageHtml")
    public Result<Integer> editPageHtml(@Validated @RequestBody DraftElementUpdatePageHtmlDto dto) {
        int element = draftelementService.updateElementPageHtml(dto);
        return Result.success(element);
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("wid") Integer wid, @RequestParam("deid") Long deid) {
        return Result.success(draftelementService.delById(wid, deid));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DraftElementVo> getInfo(@RequestParam("wid") Integer wid, @RequestParam("deid") Long deid, @RequestParam("isDel") Integer isDel) {

        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        return Result.success(draftelementService.getInfo(wid, deid, userid, isDel));
    }

    /**
     * 根据id获取详情
     * 1表示查看删除得，0表示不看删除得
     */
    @GetMapping("/getInfoAdmin")
    @Operation(summary = "getInfoAdmin", description = "根据id获取详情")
    public Result<DraftElementVo> getInfoAdmin(@RequestParam("wid") Integer wid, @RequestParam("deid") Long deid, @RequestParam("isDel") Integer isDel) {
        manageService.isCheck(wid);
        return Result.success(draftelementService.getInfo(wid, deid, null, isDel));
    }

    @GetMapping("/issue")
    @Operation(summary = "issue", description = "发布")
    public Result<SimpleElementVo> issue(@RequestParam("wid") Integer wid, @RequestParam("deid") Long deid) {
        return Result.success(draftelementService.issue(wid, deid));
    }

    @GetMapping("/issueClose")
    @Operation(summary = "issueClose", description = "取消发布")
    public Result<Integer> issueClose(@RequestParam("wid") Integer wid, @RequestParam("deid") Long deid) {
        return Result.success(draftelementService.issueClose(wid, deid));
    }

    @PostMapping("/audit")
    @Operation(summary = "audit", description = "审核")
    public Result<Integer> audit(@RequestBody AuditDto dto) {
        return Result.success(draftelementService.audit(dto));
    }
}

