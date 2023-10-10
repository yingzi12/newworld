package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementContent;
import com.xinshijie.wiki.dto.ElementContentDto;
import com.xinshijie.wiki.dto.ElementContentFindDto;
import com.xinshijie.wiki.service.IElementContentService;
import com.xinshijie.wiki.vo.ElementContentVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 元素内容Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "AdminElementContentController", description = "后台-元素内容")
@RequestMapping("/admin/elementContent")
public class AdminElementContentController extends BaseController {
    @Autowired
    private IElementContentService elementContentService;

    /**
     * 查询元素内容列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ElementContentVo>> list(ElementContentFindDto elementContent) {
        startPage();
        List<ElementContentVo> list = elementContentService.selectElementContentList(elementContent);
        return getDataTable(list);
    }


    /**
     * 获取元素内容详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementContentVo> getInfo(@PathVariable("id") Long id) {
        return Result.success(elementContentService.getInfo(id));
    }

    /**
     * 新增元素内容
     */
    @Log(title = "元素内容", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementContent> add(@RequestBody ElementContentDto elementContent) {
        return Result.success(elementContentService.add(elementContent));
    }

    /**
     * 修改元素内容
     */
    @Log(title = "元素内容", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody ElementContentDto elementContent) {
        return toAjax(elementContentService.edit(elementContent));
    }

}
