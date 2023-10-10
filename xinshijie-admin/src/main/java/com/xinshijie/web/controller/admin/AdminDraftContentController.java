package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.DraftContent;
import com.xinshijie.wiki.dto.DraftContentDto;
import com.xinshijie.wiki.service.IDraftContentService;
import com.xinshijie.wiki.vo.DraftContentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 元素内容草稿 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AdminDraftContentController", description = "后台-元素内容草稿")
@RequestMapping("/admin/DraftContent")
public class AdminDraftContentController extends BaseController {
    @Autowired
    private IDraftContentService draftcontentService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询元素内容草稿", description = "查询元素内容草稿")
    public Result<List<DraftContentVo>> list(@RequestBody DraftContentDto dto) {
        List<DraftContentVo> values = draftcontentService.selectDraftContentList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询元素内容草稿", description = "分页查询元素内容草稿")
    public Result<Page<DraftContentVo>> selectSysRoleList(@RequestBody DraftContentDto dto) {
        Page<DraftContentVo> values = draftcontentService.selectPageDraftContent(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增元素内容草稿", description = "新增元素内容草稿")
    public Result<DraftContent> add(@RequestBody DraftContentDto dto) {
        return Result.success(draftcontentService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody DraftContentDto dto) {
        return Result.success(draftcontentService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(draftcontentService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DraftContentVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(draftcontentService.getInfo(id));
    }
}

