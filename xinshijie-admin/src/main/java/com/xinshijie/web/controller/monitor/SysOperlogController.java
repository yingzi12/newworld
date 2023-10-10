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
//import com.xinshijie.system.domain.SysOperLog;
//import com.xinshijie.system.service.ISysOperLogService;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 操作日志记录
// *
// * @author xinshijie
// */
//@Slf4j
//@RestController
//@RequestMapping("/monitor/operlog")
//public class SysOperlogController extends BaseController
//{
//    @Autowired
//    private ISysOperLogService operLogService;
//
//    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysOperLog operLog)
//    {
//        startPage();
//        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
//        return getDataTable(list);
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
//    @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysOperLog operLog)
//    {
//        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
//        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
//        util.exportExcel(response, list, "操作日志");
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.DELETE)
//    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
//    @DeleteMapping("/{operIds}")
//    public Result<String>  remove(@PathVariable Long[] operIds)
//    {
//        return toAjax(operLogService.deleteOperLogByIds(operIds));
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
//    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
//    @DeleteMapping("/clean")
//    public Result<String>  clean()
//    {
//        operLogService.cleanOperLog();
//        return Result.success();
//    }
//}
