package com.xinshijie.web.controller.admin;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.ApplyManageDto;
import com.xinshijie.wiki.dto.ApplyManageFindDto;
import com.xinshijie.wiki.service.IApplyManageService;
import com.xinshijie.wiki.vo.ApplyManageVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@Slf4j
@RestController
@Tag(name = "后台管理员", description = "后台-申请管理员")
@RequestMapping("/admin/applyManage")
public class AdminApplyManageController extends BaseController {

    @Autowired
    private IApplyManageService applyManageService;

    /**
     * 根据wid查询申请列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ApplyManageVo>> list(ApplyManageFindDto findDto) {
        startPage();
        List<ApplyManageVo> list = applyManageService.list(findDto);
        return getDataTable(list);
    }
    /**
     * 查询世界评论列表
     */
    @GetMapping("/getApplyManageList")
    public TableDataInfo<List<ApplyManageVo>> getWorldManageList(@Valid @NotNull(message = "缺少必须的参数") @Param("wid") Integer wid) {
        List<ApplyManageVo> list = applyManageService.getWorldManageList(wid);
        return getDataTable(list);
    }

    /**
     * 查询本人的申请列表
     */
    @GetMapping("/listUser")
    public TableDataInfo<List<ApplyManageVo>> listUser() {
        startPage();
        List<ApplyManageVo> list = applyManageService.listUser(getUserId());
        return getDataTable(list);
    }


    /**
     * 发起申请
     */
    @PostMapping("/add")
    public Result<ApplyManageVo> add(@RequestBody ApplyManageDto dto) {
        LoginUser user= getLoginUser();
        dto.setApplyName(user.getUsername());
        dto.setApplyId(user.getUserId());
        dto.setApplyTime(LocalDateTime.now());
        return Result.success(applyManageService.add(dto));

    }

    /**
     * 审核申请
     */
    @PostMapping("/audit")
    public Result<Integer> audit(@RequestBody ApplyManageDto dto) {
        LoginUser user= getLoginUser();
        dto.setAuditName(user.getUsername());
        dto.setAuditId(user.getUserId());
        dto.setAuditTime(LocalDateTime.now());
        return Result.success(applyManageService.audit(dto));
    }

    /**
     * 关闭申请
     */
    @GetMapping("/close")
    public Result<Integer> close(Long id) {
        return Result.success(applyManageService.close(id, getUserId()));
    }


}
