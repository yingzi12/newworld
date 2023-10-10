package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.AuditContentDto;
import com.xinshijie.wiki.service.IAuditContentService;
import com.xinshijie.wiki.vo.AuditContentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 元素内容审核 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AuditContentController", description = "元素内容审核")
@RequestMapping("/wiki/AuditContent")
public class AuditContentController extends BaseController {
    @Autowired
    private IAuditContentService auditcontentService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询元素内容审核", description = "查询元素内容审核")
    public Result<List<AuditContentVo>> list(@RequestBody AuditContentDto dto) {
        List<AuditContentVo> values = auditcontentService.selectAuditContentList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询元素内容审核", description = "分页查询元素内容审核")
    public Result<Page<AuditContentVo>> selectSysRoleList(@RequestBody AuditContentDto dto) {
        Page<AuditContentVo> values = auditcontentService.selectPageAuditContent(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增元素内容审核", description = "新增元素内容审核")
    public Result<Integer> add(@RequestBody AuditContentDto dto) {
        return Result.success(auditcontentService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody AuditContentDto dto) {
        return Result.success(auditcontentService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(auditcontentService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<AuditContentVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(auditcontentService.getInfo(id));
    }
}

