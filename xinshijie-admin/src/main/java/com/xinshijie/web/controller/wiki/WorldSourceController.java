package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.WorldSource;
import com.xinshijie.wiki.dto.WorldSourceDto;
import com.xinshijie.wiki.service.IWorldSourceService;
import com.xinshijie.wiki.vo.WorldSourceVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 来源 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Slf4j
@RestController
@Tag(name = "WorldSourceController", description = "世界来源")
@RequestMapping("/wiki/worldSoruce")
public class WorldSourceController extends BaseController {
    @Autowired
    private IWorldSourceService worldsourceService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询来源", description = "查询来源")
    public Result<List<WorldSourceVo>> list(@RequestBody WorldSourceDto dto) {
        List<WorldSourceVo> values = worldsourceService.selectWorldSourceList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询来源", description = "分页查询来源")
    public Result<Page<WorldSourceVo>> selectSysRoleList(@RequestBody WorldSourceDto dto) {
        Page<WorldSourceVo> values = worldsourceService.selectPageWorldSource(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增来源", description = "新增来源")
    public Result<WorldSource> add(@RequestBody WorldSourceDto dto) {
        return Result.success(worldsourceService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody WorldSourceDto dto) {
        return Result.success(worldsourceService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(worldsourceService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<WorldSourceVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(worldsourceService.getInfo(id));
    }
}

