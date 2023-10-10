package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.DraftStatusEnums;
import com.xinshijie.wiki.domain.Chapter;
import com.xinshijie.wiki.dto.ChapterDto;
import com.xinshijie.wiki.dto.ChapterFindDto;
import com.xinshijie.wiki.dto.ChapterUpdateDto;
import com.xinshijie.wiki.service.IChapterService;
import com.xinshijie.wiki.vo.ChapterVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 故事章节 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Slf4j
@RestController
@Tag(name = "AdminChapterController", description = "后台-故事章节")
@RequestMapping("/admin/chapter")
public class AdminChapterController extends BaseController {
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询所有的章节,需要管理员权限
     */
    @GetMapping("/list")
    @Operation(summary = "查询故事章节", description = "查询故事章节")
    public TableDataInfo<List<ChapterVo>> list(ChapterFindDto dto) {
        startPage();
        List<ChapterVo> list = chapterService.selectChapterList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询故事章节", description = "分页查询故事章节")
    public Result<Page<ChapterVo>> selectSysRoleList(@RequestBody ChapterDto dto) {
        Page<ChapterVo> values = chapterService.selectPageChapter(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增故事目录分卷", description = "新增故事目录分卷")
    public Result<Chapter> add(@RequestBody ChapterDto dto) {
        dto.setLevel(0);
        dto.setPid(0L);
        dto.setStatus(DraftStatusEnums.ISSUE.getCode());
        dto.setContent("");
        dto.setCreateId(getUserId());
        dto.setCreateName(getUsername());
        dto.setCreateTime(LocalDateTime.now());
        return Result.success(chapterService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PutMapping("/updateName")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> updateName(@RequestBody ChapterUpdateDto updateDto) {
        ChapterDto dto = new ChapterDto();
        dto.setId(updateDto.getId());
        dto.setSid(updateDto.getSid());
        dto.setTitle(updateDto.getTitle());
        dto.setUpdateId(getUserId());
        dto.setUpdateName(getUsername());
        dto.setUpdateTime(LocalDateTime.now());
        return Result.success(chapterService.edit(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody ChapterDto dto) {
        return Result.success(chapterService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("sid") Long sid, @RequestParam("scid") Long scid) {
        return Result.success(chapterService.delById(sid, scid));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<ChapterVo> getInfo(@RequestParam("sid") Long sid, @RequestParam("scid") Long scid) {
        return Result.success(chapterService.getInfo(sid, scid));
    }
}

