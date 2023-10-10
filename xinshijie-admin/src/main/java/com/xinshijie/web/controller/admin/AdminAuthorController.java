package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.system.service.ISysUserService;
import com.xinshijie.wiki.domain.Author;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.dto.AuthorFindDto;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.vo.AuthorVo;
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
@Tag(name = "AdminAuthorController", description = "后台-作家")
@RequestMapping("/admin/author")
public class AdminAuthorController extends BaseController {
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询管理员数列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<AuthorVo>> list(AuthorFindDto redident) {
        startPage();
        List<AuthorVo> list = authorService.selectAuthorList(redident);
        return getDataTable(list);
    }

    /**
     * 查询世界评论列表
     */
    @GetMapping("/getStoryAuthor")
    public Result<List<AuthorVo>> getStoryAuthor(@Valid @NotNull(message = "缺少必须的参数") @Param("sid") Long sid) {
        List<AuthorVo> list = authorService.selectBySid(sid);
        return Result.success(list);
    }

    /**
     * 新增管理员数
     */
    @Log(title = "管理员数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<SysUser> add(@RequestBody AuthorDto redident) {
        SysUser sysUser = userService.selectUserById(redident.getUserId());
        if (sysUser == null) {
            throw new ServiceException(ResultCodeEnum.USERNAME_DOES_NOT_EXIST);
        }
        if (!sysUser.getUserName().equals(redident.getUserName())) {
            throw new ServiceException( ResultCodeEnum.USERNAME_DOES_NOT_MATCH_ID);
        }
        return toAjax(authorService.insertAuthor(redident));
    }

    /**
     * 修改管理员数
     */
    @Log(title = "管理员数", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody Author redident) {
        return toAjax(authorService.updateById(redident));
    }

    /**
     * 删除管理员数
     */
    @Log(title = "管理员数", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    public Result<String> remove(@RequestParam("id") Integer id, @RequestParam("sid") Long sid) {
        return toAjax(authorService.deleteAuthorById(id, sid));
    }

    /**
     * 获取管理员数详细信息
     */
    @GetMapping(value = "/getInfo")
    public Result<AuthorVo> getInfo(@Param("sid") Long sid) {
        Long userid = SecurityUtils.getUserId();
        return Result.success(authorService.selectBySidUserId(sid, userid));
    }
}
