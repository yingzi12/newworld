package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.service.IHardingService;
import com.xinshijie.wiki.vo.HardingVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
@Tag(name = "HardingController", description = "收藏")
@RequestMapping("/wiki/harding")
public class HardingController extends BaseController {
    @Autowired
    private IHardingService hardingService;

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<HardingVo> getInfo(@RequestParam("id") Long id) {
        if (getLoginUserNull() == null) {
            return Result.success(null);
        }
        return Result.success(hardingService.getInfo(id));
    }


    @GetMapping("/getInfoBySid")
    @Operation(summary = "getInfoBySid", description = "根据id获取详情")
    public Result<HardingVo> getInfoBySid(@RequestParam("sid") Long sid) {
        if (getLoginUserNull() == null) {
            return Result.success(null);
        }
        return Result.success(hardingService.getInfoBySidUserid(sid, getUserId()));
    }

}

