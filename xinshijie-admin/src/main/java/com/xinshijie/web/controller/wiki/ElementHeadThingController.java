package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementHeadThing;
import com.xinshijie.wiki.service.IElementHeadThingService;
import com.xinshijie.wiki.dto.ElementHeadThingDto;
import com.xinshijie.wiki.vo.ElementHeadThingVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  物品/材料,元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " ElementHeadThingController", description = "后台- 物品/材料,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/elementHeadThing")
public class  ElementHeadThingController  extends BaseController{

    @Autowired
    private IElementHeadThingService elementHeadThingService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementHeadThing> add(@RequestBody  ElementHeadThingDto dto){
        ElementHeadThing vo = elementHeadThingService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = elementHeadThingService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementHeadThingDto dto){
        Integer  vo = elementHeadThingService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementHeadThingVo> getInfo(@PathVariable("id") Long id) {
        ElementHeadThingVo vo = elementHeadThingService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<ElementHeadThingVo>> select(@RequestBody ElementHeadThingDto findDto){
        Page<ElementHeadThingVo> vo = elementHeadThingService.selectPageElementHeadThing(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<ElementHeadThingVo>> listElementHeadThing(ElementHeadThingDto findDto) {
        startPage();
        List<ElementHeadThingVo> list = elementHeadThingService.selectElementHeadThingList(findDto);
        return getDataTable(list);
    }

}
