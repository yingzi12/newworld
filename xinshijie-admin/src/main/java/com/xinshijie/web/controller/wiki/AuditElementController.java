package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.AuditElementDto;
import com.xinshijie.wiki.service.IAuditElementService;
import com.xinshijie.wiki.vo.AuditElementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 元素审核 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AuditElementController", description = "元素审核")
@RequestMapping("/wiki/AuditElement")
public class AuditElementController extends BaseController {
    @Autowired
    private IAuditElementService auditelementService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询元素审核", description = "查询元素审核")
    public Result<List<AuditElementVo>> list(@RequestBody AuditElementDto dto) {
        List<AuditElementVo> values = auditelementService.selectAuditElementList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询元素审核", description = "分页查询元素审核")
    public Result<Page<AuditElementVo>> selectSysRoleList(@RequestBody AuditElementDto dto) {
        Page<AuditElementVo> values = auditelementService.selectPageAuditElement(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增元素审核", description = "新增元素审核")
    public Result<Integer> add(@RequestBody AuditElementDto dto) {
        return Result.success(auditelementService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody AuditElementDto dto) {
        return Result.success(auditelementService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(auditelementService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<AuditElementVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(auditelementService.getInfo(id));
    }
}

