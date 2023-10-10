package com.xinshijie.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinshijie.common.constant.HttpStatus;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.page.PageDomain;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.page.TableSupport;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.common.utils.PageUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.common.utils.sql.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import static com.xinshijie.common.enums.ResultCodeEnum.SYSTEM_INNER_ERROR;

/**
 * web层通用数据处理
 *
 * @author xinshijie
 */
@Slf4j
public class BaseController<T> {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage() {
        PageUtils.clearPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo<T> getDataTable(List<T> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public Result<String> success() {
        return Result.success();
    }

    /**
     * 返回失败消息
     */
    public Result<String> error() {
        return Result.error(SYSTEM_INNER_ERROR);
    }

    /**
     * 返回失败消息
     */
    public Result<String> error(String message) {
        return Result.error(SYSTEM_INNER_ERROR.getCode(), message);
    }

    /**
     * 返回成功消息
     */
    public Result<String> success(String message) {
        return Result.success(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result<String> toAjax(int rows) {
        return rows > 0 ? Result.success() : Result.error(SYSTEM_INNER_ERROR);
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected Result<String> toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }

    public LoginUser getLoginUserNull() {
        return SecurityUtils.getLoginUserNull();
    }


    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取登录部门id
     */
    public Long getDeptId() {
        return getLoginUser().getDeptId();
    }

    /**
     * 获取登录用户名
     */
    public String getUsername() {
        return getLoginUser().getUsername();
    }

    public String getAvatar() {
        return getLoginUser().getAvatar();
    }

    public String getNickName() {
        return getLoginUser().getNickName();
    }
}
