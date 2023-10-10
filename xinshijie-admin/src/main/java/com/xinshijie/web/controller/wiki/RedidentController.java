package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.RedidentDto;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.RedidentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 居民数 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "RedidentController", description = "居民数")
@RequestMapping("/wiki/Redident")
public class RedidentController extends BaseController {
    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询居民数", description = "查询居民数")
    public TableDataInfo<List<RedidentVo>> list(RedidentDto dto) {
        startPage();
        List<RedidentVo> list = redidentService.selectRedidentList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询居民数", description = "分页查询居民数")
    public Result<Page<RedidentVo>> selectSysRoleList(@RequestBody RedidentDto dto) {
        Page<RedidentVo> values = redidentService.selectPageRedident(dto);
        return Result.success(values);
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<RedidentVo> getInfo(@RequestParam("id") Integer id) {
        return Result.success(redidentService.getInfo(id));
    }
}

