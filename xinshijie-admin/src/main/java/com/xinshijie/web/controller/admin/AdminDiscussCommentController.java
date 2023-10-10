package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.DiscussComment;
import com.xinshijie.wiki.dto.DiscussCommentAddDto;
import com.xinshijie.wiki.dto.DiscussCommentDto;
import com.xinshijie.wiki.dto.DiscussCommentReplyAddDto;
import com.xinshijie.wiki.service.IDiscussCommentService;
import com.xinshijie.wiki.vo.DiscussCommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 讨论回复表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@RestController
@Tag(name = "AdminDiscussCommentController", description = "后台-讨论回复表")
@RequestMapping("/admin/discussComment")
public class AdminDiscussCommentController extends BaseController {
    @Autowired
    private IDiscussCommentService discusscommentService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询讨论回复表", description = "查询讨论回复表")
    public TableDataInfo<List<DiscussCommentVo>> list(DiscussCommentDto dto) {
        startPage();
        List<DiscussCommentVo> list = discusscommentService.selectDiscussCommentList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询讨论回复表", description = "分页查询讨论回复表")
    public Result<Page<DiscussCommentVo>> selectSysRoleList(@RequestBody DiscussCommentDto dto) {
        Page<DiscussCommentVo> values = discusscommentService.selectPageDiscussComment(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增讨论回复表", description = "新增讨论回复表")
    public Result<DiscussComment> add(@RequestBody DiscussCommentAddDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1 && dto.getWid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        if (dto.getSource() == 2 && dto.getSid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        String key = "";
        if (dto.getSid() == null) {
            key = CacheConstants.DISREPLY + getUserId() + ":" + dto.getSource() + ":" + dto.getDid();
        } else {
            key = CacheConstants.DISREPLY + getUserId() + ":" + dto.getSource() + ":" + dto.getDid();
        }
//        if (redisCache.hasKey(key)) {
//            throw new ServiceException(ResultCodeEnum.POSTING_TOO_FREQUENTLY);
//        } else {
//            redisCache.setCacheInteger(key, 1, 1, TimeUnit.HOURS);
//        }
//        dto.setCircleUrl(getAvatar());
        return Result.success(discusscommentService.add(dto));
    }
    @PostMapping("/reply")
    @Operation(summary = "讨论回复表", description = "新增讨论回复表")
    public Result<DiscussComment> reply(@RequestBody DiscussCommentReplyAddDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1 && dto.getWid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        if (dto.getSource() == 2 && dto.getSid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        String key = "";
        if (dto.getSid() == null) {
            key = CacheConstants.DISREPLY + getUserId() + ":" + dto.getSource() + ":" + dto.getDid();
        } else {
            key = CacheConstants.DISREPLY + getUserId() + ":" + dto.getSource() + ":" + dto.getDid();
        }
//        if (redisCache.hasKey(key)) {
//            throw new ServiceException(ResultCodeEnum.POSTING_TOO_FREQUENTLY);
//        } else {
//            redisCache.setCacheInteger(key, 1, 1, TimeUnit.HOURS);
//        }
//        dto.setCircleUrl(getAvatar());
        return Result.success(discusscommentService.reply(dto));
    }
    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody DiscussCommentDto dto) {
        return Result.success(discusscommentService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(discusscommentService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DiscussCommentVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(discusscommentService.getInfo(id));
    }
}

