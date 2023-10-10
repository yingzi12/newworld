//package com.xinshijie.web.controller.monitor;
//
//import java.util.List;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.xinshijie.common.annotation.Log;
//import com.xinshijie.common.core.controller.BaseController;
//import com.xinshijie.common.core.vo.Result;
//import com.xinshijie.common.core.page.TableDataInfo;
//import com.xinshijie.common.enums.BusinessType;
//import com.xinshijie.common.utils.poi.ExcelUtil;
//import com.xinshijie.framework.web.service.SysPasswordService;
//import com.xinshijie.system.domain.SysLogininfor;
//import com.xinshijie.system.service.ISysLogininforService;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 系统访问记录
// *
// * @author xinshijie
// */
//@Slf4j
//@RestController
//@RequestMapping("/monitor/logininfor")
//public class SysLogininforController extends BaseController
//{
//    @Autowired
//    private ISysLogininforService logininforService;
//
//    @Autowired
//    private SysPasswordService passwordService;
//
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
//    @GetMapping("/list")
//    public TableDataInfo<List<SysLogininfor>> list(SysLogininfor logininfor)
//    {
//        startPage();
//        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
//        return getDataTable(list);
//    }
//
//    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysLogininfor logininfor)
//    {
//        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
//        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
//        util.exportExcel(response, list, "登录日志");
//    }
//
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
//    @Log(title = "登录日志", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{infoIds}")
//    public Result<String>  remove(@PathVariable Long[] infoIds)
//    {
//        return toAjax(logininforService.deleteLogininforByIds(infoIds));
//    }
//
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
//    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
//    @DeleteMapping("/clean")
//    public Result<String>  clean()
//    {
//        logininforService.cleanLogininfor();
//        return success();
//    }
//
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:unlock')")
//    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
//    @GetMapping("/unlock/{userName}")
//    public Result<String>  unlock(@PathVariable("userName") String userName)
//    {
//        passwordService.clearLoginRecordCache(userName);
//        return success();
//    }
//}
