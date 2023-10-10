package com.xinshijie.web.controller.admin;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.ApplyAuthorDto;
import com.xinshijie.wiki.dto.ApplyAuthorFindDto;
import com.xinshijie.wiki.service.IApplyAuthorService;
import com.xinshijie.wiki.vo.ApplyAuthorVo;
import com.xinshijie.wiki.vo.ManageVo;
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
@Tag(name = "AdminApplyAuthorController", description = "后台-申请作家")
@RequestMapping("/admin/applyAuthor")
public class AdminApplyAuthorController extends BaseController {

    @Autowired
    private IApplyAuthorService applyAuthorService;


    /**
     * 根据wid查询申请列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ApplyAuthorVo>> list(ApplyAuthorFindDto dto) {
        startPage();
        List<ApplyAuthorVo> list = applyAuthorService.list(dto);
        return getDataTable(list);
    }

    /**
     * 查询待审核的记录
     */
    @GetMapping("/getApplyAuthorList")
    public TableDataInfo<List<ApplyAuthorVo>> getApplyAuthorList(@Valid @NotNull(message = "缺少必须的参数") @Param("wid") Integer wid,@Valid @NotNull(message = "缺少必须的参数") @RequestParam("sid") Long sid) {
        List<ApplyAuthorVo> list = applyAuthorService.getApplyAuthorList(wid, sid);
        return getDataTable(list);
    }


    /**
     * 查询本人的申请列表
     */
    @GetMapping("/listUser")
    public TableDataInfo<List<ApplyAuthorVo>> listUser() {
        startPage();
        List<ApplyAuthorVo> list = applyAuthorService.listUser(getUserId());
        return getDataTable(list);
    }


    /**
     * 发起申请
     */
    @PostMapping("/add")
    public Result<ApplyAuthorVo> add(@RequestBody ApplyAuthorDto dto) {
        LoginUser user= getLoginUser();
        dto.setApplyName(user.getUsername());
        dto.setApplyId(user.getUserId());
        dto.setApplyTime(LocalDateTime.now());
        return Result.success(applyAuthorService.add(dto));

    }

    /**
     * 审核申请
     */
    @PostMapping("/audit")
    public Result<Integer> audit(@RequestBody ApplyAuthorDto dto) {
        LoginUser user= getLoginUser();
        dto.setAuditName(user.getUsername());
        dto.setAuditId(user.getUserId());
        dto.setAuditTime(LocalDateTime.now());
        return Result.success(applyAuthorService.audit(dto));
    }

    /**
     * 关闭申请
     */
    @GetMapping("/close")
    public Result<Integer> close(Long id) {
        return Result.success(applyAuthorService.close(id, getUserId()));
    }

}
