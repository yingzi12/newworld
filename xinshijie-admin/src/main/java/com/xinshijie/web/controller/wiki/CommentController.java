package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.CommentDto;
import com.xinshijie.wiki.dto.CommentFindDto;
import com.xinshijie.wiki.service.ICommentService;
import com.xinshijie.wiki.vo.CommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "CommentController", description = "评论表")
@RequestMapping("/wiki/comment")
public class CommentController extends BaseController {
    @Autowired
    private ICommentService commentService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询评论表", description = "查询评论表")
    public TableDataInfo<List<CommentVo>> list(CommentFindDto dto) {
        startPage();
        List<CommentVo> list = commentService.selectCommentList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询评论表", description = "分页查询评论表")
    public Result<Page<CommentVo>> selectSysRoleList(@RequestBody CommentDto dto) {
        Page<CommentVo> values = commentService.selectPageComment(dto);
        return Result.success(values);
    }

    /**
     * 查询世界评论列表
     */
    @GetMapping("/getWorldComment")
    public TableDataInfo<List<CommentVo>> getWorldComment(CommentFindDto dto) {
        startPage();
        List<CommentVo> list = commentService.selectCommentByWorld(dto);
        return getDataTable(list);
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<CommentVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(commentService.getInfo(id));
    }
}

