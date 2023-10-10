package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Discuss;
import com.xinshijie.wiki.dto.DiscussDto;
import com.xinshijie.wiki.service.IDiscussService;
import com.xinshijie.wiki.vo.DiscussVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讨论主题表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@RestController
@Tag(name = "DiscussController", description = "讨论主题表")
@RequestMapping("/wiki/discuss")
public class DiscussController extends BaseController {
    @Autowired
    private IDiscussService discussService;


    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询讨论主题表", description = "查询讨论主题表")
    public TableDataInfo<List<DiscussVo>> list(DiscussDto dto) {
        startPage();
        List<DiscussVo> list = discussService.selectDiscussList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询讨论主题表", description = "分页查询讨论主题表")
    public Result<Page<DiscussVo>> selectSysRoleList(@RequestBody DiscussDto dto) {
        Page<DiscussVo> values = discussService.selectPageDiscuss(dto);
        return Result.success(values);
    }

    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增讨论主题表", description = "新增讨论主题表")
    public Result<Discuss> add(@RequestBody DiscussDto dto) {
        return Result.success(discussService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody DiscussDto dto) {
        return Result.success(discussService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(discussService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DiscussVo> getInfo(@RequestParam("did") Long did) {
        return Result.success(discussService.getInfo(did));
    }
}

