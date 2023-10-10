package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Fllow;
import com.xinshijie.wiki.dto.FllowDto;
import com.xinshijie.wiki.service.IFllowService;
import com.xinshijie.wiki.vo.FllowVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 关注 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AdminFllowController", description = "后台-关注")
@RequestMapping("/admin/fllow")
public class AdminFllowController extends BaseController {

    @Autowired
    private IFllowService fllowService;

    /**
     * 查询所有
     */
    @Operation(summary = "查询关注", description = "查询关注")
    @GetMapping("/list")
    public TableDataInfo<List<FllowVo>> list(FllowDto dto) {
        startPage();
        List<FllowVo> list = fllowService.selectFllowList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询关注", description = "分页查询关注")
    public Result<Page<FllowVo>> selectSysRoleList(@RequestBody FllowDto dto) {
        Page<FllowVo> values = fllowService.selectPageFllow(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增关注", description = "新增关注")
    public Result<Fllow> add(@RequestBody FllowDto dto) {
        dto.setCreateId(getUserId());
        dto.setCreateName(getUsername());
        dto.setCreateTime(LocalDateTime.now());
        return Result.success(fllowService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody FllowDto dto) {
        return Result.success(fllowService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(fllowService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<FllowVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(fllowService.getInfo(id));
    }

    @GetMapping("/getInfoByWid")
    @Operation(summary = "getInfoByWid", description = "根据id获取详情")
    public Result<FllowVo> getInfoByWid(@RequestParam("wid") Integer wid) {
        return Result.success(fllowService.getInfoByWidUserid(wid, getUserId()));
    }


    @GetMapping("/fllow")
    @Operation(summary = "fllow", description = "关注")
    public Result<Integer> fllow(@RequestParam("wid") Integer wid) {
        return Result.success(fllowService.fllow(wid));
    }


    @GetMapping("/fllowClose")
    @Operation(summary = "fllowClose", description = "取消关注")
    public Result<Integer> fllowClose(@RequestParam("wid") Integer wid) {
        return Result.success(fllowService.fllowClose(wid));
    }

}

