package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.annotation.WorldVelidate;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.dto.ElementAddDto;
import com.xinshijie.wiki.dto.ElementFindDto;
import com.xinshijie.wiki.dto.ElementUpdateDto;
import com.xinshijie.wiki.service.IElementService;
import com.xinshijie.wiki.vo.ElementVo;
import com.xinshijie.wiki.vo.SimpleElementVo;
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
 * 管理员操作
 * 元素Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Validated
@Slf4j
@RestController
@Tag(name = "AdminElementController", description = "后台-元素")
@RequestMapping("/admin/element")
public class AdminElementController extends BaseController {
    @Autowired
    private IElementService elementService;


    /**
     * 查询元素列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ElementVo>> list(ElementFindDto findDto) {
        List<ElementVo> list = elementService.selectElementList(findDto);
        return getDataTable(list);
    }

    /**
     * 查询元素列表
     */
    @WorldVelidate
    @GetMapping("/listElementWorld")
    public TableDataInfo<List<ElementVo>> listElementWorld(ElementFindDto findDto) {
        startPage();
        List<ElementVo> list = elementService.selectElementList(findDto);
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
     * 新增元素
     */
//    @WorldVelidate
    @Log(title = "元素", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<SimpleElementVo> add(@Valid @RequestBody ElementAddDto addDto) {
        SimpleElementVo element = elementService.insertElement(addDto);
        return Result.success(element);
    }

    /**
     * 修改元素
     */
//    @WorldVelidate
    @Log(title = "元素修改", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<SimpleElementVo> edit(@Validated @RequestBody ElementUpdateDto dto) {
        SimpleElementVo element = elementService.updateElement(dto);
        return Result.success(element);
    }


    /**
     * 修改元素
     */
    @WorldVelidate
    @Log(title = "元素", businessType = BusinessType.UPDATE)
    @GetMapping("/updatePush")
    public Result<String> updatePush(@Validated @Param("wid") Integer wid, @Param("eid") Long eid) {
        ElementUpdateDto element = new ElementUpdateDto();
        element.setStatus(1);
        element.setWid(wid);
        element.setId(eid);
        return toAjax(elementService.updateStatus(element));
    }

    /**
     * 删除元素
     */
    @Log(title = "元素", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    public Result<String> remove(@Validated @NotNull(message = "世界id不能为空") @Param("wid") Integer wid, @Validated @NotNull(message = "元素id不能为空") @Param("eid") Long eid) {
        return toAjax(elementService.deleteElementByIdWid(wid, eid));
    }
}
