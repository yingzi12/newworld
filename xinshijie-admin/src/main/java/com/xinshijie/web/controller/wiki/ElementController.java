package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.ElementFindDto;
import com.xinshijie.wiki.service.IElementService;
import com.xinshijie.wiki.vo.ElementVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 元素Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "ElementController", description = "元素")
@RequestMapping("/wiki/element")
public class ElementController extends BaseController {
    @Autowired
    private IElementService elementService;

    /**
     * 查询元素列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ElementVo>> list(ElementFindDto element) {
        startPage();
        List<ElementVo> list = elementService.selectElementList(element);
        return getDataTable(list);
    }


    /**
     * 获取元素详细信息
     */
    @GetMapping(value = "/getInfo")
    public Result<ElementVo> getInfo(@RequestParam("wid") Integer wid, @RequestParam("eid") Long eid) {
        return Result.success(elementService.selectElement(wid, eid));
    }

    /**
     * 获取元素详细信息
     */
    @GetMapping(value = "/simple")
    public Result<ElementVo> getSingle(@RequestParam("wid") Integer wid, @RequestParam("eid") Long eid) {
        return Result.success(elementService.getSingle(wid, eid));
    }
}
