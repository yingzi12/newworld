package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Discuss;
import com.xinshijie.wiki.dto.DiscussDto;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IDiscussService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.vo.DiscussVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 讨论主题表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-02
 */
@Slf4j
@RestController
@Tag(name = "AdminDiscussController", description = "后台-讨论主题表")
@RequestMapping("/admin/discuss")
public class AdminDiscussController extends BaseController {
    @Autowired
    private IDiscussService DiscussService;

    @Autowired
    private IManageService manageService;

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询讨论主题表", description = "查询讨论主题表")
    public TableDataInfo<List<DiscussVo>> list(DiscussDto dto) {
        Long userid = SecurityUtils.getUserId();
        startPage();
        dto.setCreateId(userid);
        List<DiscussVo> list = DiscussService.selectDiscussList(dto);
        return getDataTable(list);
    }

    @GetMapping("/listAdmin")
    @Operation(summary = "查询讨论主题表", description = "查询讨论主题表")
    public TableDataInfo<List<DiscussVo>> listAdmin(DiscussDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1) {
            manageService.isCheck(dto.getWid());
        }
        if (dto.getSource() == 2) {
            authorService.isCheck(dto.getSid());
        }

        startPage();
        List<DiscussVo> list = DiscussService.selectDiscussList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询讨论主题表", description = "分页查询讨论主题表")
    public Result<Page<DiscussVo>> selectSysRoleList(@RequestBody DiscussDto dto) {
        Page<DiscussVo> values = DiscussService.selectPageDiscuss(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增讨论主题表", description = "新增讨论主题表")
    public Result<Discuss> add(@RequestBody DiscussDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1 && dto.getWid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_IS_INVALID);
        }
        if (dto.getSource() == 2 && dto.getSid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        String key = "";
        if (dto.getSid() == null) {
            key = CacheConstants.DISCUSS + getUserId() + ":" + dto.getSource() + ":" + dto.getWid();
        } else {
            key = CacheConstants.DISCUSS + getUserId() + ":" + dto.getSource() + ":" + dto.getSid();
        }
        if (redisCache.hasKey(key)) {
            throw new ServiceException(ResultCodeEnum.POSTED_AND_DISCUSSED_FREQUENTLY);
        } else {
            redisCache.setCacheInteger(key, 1, 1, TimeUnit.HOURS);
        }
        dto.setCircleUrl(getAvatar());

        return Result.success(DiscussService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody DiscussDto dto) {
        dto.setCreateId(getUserId());
        return Result.success(DiscussService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(DiscussService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<DiscussVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(DiscussService.getInfo(id));
    }

    /**
     * 根据id修改数据
     */
    @PutMapping("/updateStatus")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> updateStatus(@RequestBody DiscussDto dto) {
        Long userid = SecurityUtils.getUserId();
        dto.setCreateId(userid);
        return Result.success(DiscussService.editStatus(dto));
    }


    /**
     * 根据id修改数据
     */
    @PutMapping("/updateStatusAdmin")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> updateStatusAdmin(@RequestBody DiscussDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1) {
            manageService.isCheck(dto.getWid());
        }
        if (dto.getSource() == 2) {
            authorService.isCheck(dto.getSid());
        }

        return Result.success(DiscussService.editStatus(dto));
    }

}

