package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Harding;
import com.xinshijie.wiki.dto.HardingDto;
import com.xinshijie.wiki.service.IHardingService;
import com.xinshijie.wiki.vo.HardingVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AdminHardingController", description = "后台-收藏")
@RequestMapping("/admin/harding")
public class AdminHardingController extends BaseController {
    @Autowired
    private IHardingService hardingService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询收藏", description = "查询收藏")
    public TableDataInfo<List<HardingVo>> list(HardingDto dto) {
        startPage();
        List<HardingVo> list = hardingService.selectHardingList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询收藏", description = "分页查询收藏")
    public Result<Page<HardingVo>> selectSysRoleList(@RequestBody HardingDto dto) {
        Page<HardingVo> values = hardingService.selectPageHarding(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增收藏", description = "新增收藏")
    public Result<Harding> add(@RequestBody HardingDto dto) {
        dto.setCreateId(getUserId());
        dto.setCreateName(getUsername());
        dto.setCreateTime(LocalDateTime.now());
        return Result.success(hardingService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody HardingDto dto) {
        return Result.success(hardingService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(hardingService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<HardingVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(hardingService.getInfo(id));
    }


    @GetMapping("/getInfoBySid")
    @Operation(summary = "getInfoBySid", description = "根据id获取详情")
    public Result<HardingVo> getInfoBySid(@RequestParam("sid") Long sid) {
        return Result.success(hardingService.getInfoBySidUserid(sid, getUserId()));
    }

    @GetMapping("/harding")
    @Operation(summary = "harding", description = "收藏")
    public Result<Integer> harding(@RequestParam("wid") Integer wid, @RequestParam("sid") Long sid) {
        return Result.success(hardingService.harding(wid, sid));
    }


    @GetMapping("/hardingClose")
    @Operation(summary = "hardingClose", description = "取消收藏")
    public Result<Integer> hardingClose(@RequestParam("sid") Long sid) {
        return Result.success(hardingService.hardingClose(sid));
    }
}

