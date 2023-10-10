package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.DiscussCommentDto;
import com.xinshijie.wiki.service.IDiscussCommentService;
import com.xinshijie.wiki.vo.DiscussCommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讨论回复表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@RestController
@Tag(name = "DiscussCommentController", description = "讨论回复表")
@RequestMapping("/wiki/discussComment")
public class DiscussCommentController extends BaseController {
    @Autowired
    private IDiscussCommentService discusscommentService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询讨论回复表", description = "查询讨论回复表")
    public TableDataInfo<List<DiscussCommentVo>> list(DiscussCommentDto dto) {
        startPage();
        List<DiscussCommentVo> list = discusscommentService.selectDiscussCommentList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询讨论回复表", description = "分页查询讨论回复表")
    public Result<Page<DiscussCommentVo>> selectSysRoleList(@RequestBody DiscussCommentDto dto) {
        Page<DiscussCommentVo> values = discusscommentService.selectPageDiscussComment(dto);
        return Result.success(values);
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DiscussCommentVo> getInfo(@RequestParam("dcid") Long dcid) {
        return Result.success(discusscommentService.getInfo(dcid));
    }
}

