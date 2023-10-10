package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.BlacklistDto;
import com.xinshijie.wiki.service.IBlacklistService;
import com.xinshijie.wiki.vo.BlacklistVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "BlacklistController", description = "世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作")
@RequestMapping("/wiki/Blacklist")
public class BlacklistController extends BaseController {
    @Autowired
    private IBlacklistService blacklistService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作", description = "查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作")
    public Result<List<BlacklistVo>> list(@RequestBody BlacklistDto dto) {
        List<BlacklistVo> values = blacklistService.selectBlacklistList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作", description = "分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作")
    public Result<Page<BlacklistVo>> selectSysRoleList(@RequestBody BlacklistDto dto) {
        Page<BlacklistVo> values = blacklistService.selectPageBlacklist(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作", description = "新增世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作")
    public Result<Integer> add(@RequestBody BlacklistDto dto) {
        return Result.success(blacklistService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody BlacklistDto dto) {
        return Result.success(blacklistService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(blacklistService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<BlacklistVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(blacklistService.getInfo(id));
    }
}

