package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.annotation.WorldVelidate;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.system.service.ISysUserService;
import com.xinshijie.wiki.domain.Manage;
import com.xinshijie.wiki.dto.ManageDto;
import com.xinshijie.wiki.dto.ManageFindDto;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.vo.ManageVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员数Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Validated
@Slf4j
@RestController
@Tag(name = "AdminManageController", description = "后台-管理员")
@RequestMapping("/admin/manage")
public class AdminManageController extends BaseController {
    @Autowired
    private IManageService manageService;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询管理员数列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ManageVo>> list(ManageFindDto redident) {
        startPage();
        List<ManageVo> list = manageService.selectManageList(redident);
        return getDataTable(list);
    }

    /**
     * 查询世界评论列表
     */
    @GetMapping("/getWorldManage")
    public TableDataInfo<List<ManageVo>> getWorldManage(@Valid @NotNull(message = "缺少必须的参数") @Param("wid") Integer wid) {
        List<ManageVo> list = manageService.selectManageByWid(wid);
        return getDataTable(list);
    }

    /**
     * 获取管理员数详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<ManageVo> getInfo(@PathVariable("id") Long id) {
        return Result.success(manageService.getInfo(id));
    }

    /**
     * 新增管理员数
     */
    @WorldVelidate
    @Log(title = "管理员数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<Integer> add(@RequestBody ManageDto redident) {
        SysUser sysUser = userService.selectUserById(redident.getUserId());
        if (sysUser == null) {
            throw new ServiceException(ResultCodeEnum.USERNAME_DOES_NOT_EXIST);
        }
        if (!sysUser.getUserName().equals(redident.getUserName())) {
            throw new ServiceException( ResultCodeEnum.USERNAME_DOES_NOT_MATCH_ID);
        }
        return toAjax(manageService.insertManage(redident));
    }

    /**
     * 修改管理员数
     */
    @Log(title = "管理员数", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody Manage redident) {
        return toAjax(manageService.updateById(redident));
    }

    /**
     * 删除管理员数
     */
    @Log(title = "管理员数", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestParam("id") Long id, @RequestParam("wid") Integer wid) {
        return toAjax(manageService.deleteManageById(id, wid));
    }

    /**
     * 获取管理员数详细信息
     */
    @GetMapping(value = "/getInfo")
    public Result<ManageVo> getInfo(@Param("wid") Integer wid) {
        Long userid = SecurityUtils.getUserId();
        return Result.success(manageService.selectByWidUserId(wid, userid));
    }
}
