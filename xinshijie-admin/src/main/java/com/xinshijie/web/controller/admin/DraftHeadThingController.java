package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadThing;
import com.xinshijie.wiki.service.IDraftHeadThingService;
import com.xinshijie.wiki.dto.DraftHeadThingDto;
import com.xinshijie.wiki.vo.DraftHeadThingVo;
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
@Tag(name = " DraftHeadThingController", description = "后台- 物品/材料,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadThing")
public class  DraftHeadThingController  extends BaseController{

    @Autowired
    private IDraftHeadThingService draftHeadThingService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadThing> add(@RequestBody  DraftHeadThingDto dto){
        DraftHeadThing vo = draftHeadThingService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadThingService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadThingDto dto){
        Integer  vo = draftHeadThingService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadThingVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadThingVo vo = draftHeadThingService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadThingVo>> select(@RequestBody DraftHeadThingDto findDto){
        Page<DraftHeadThingVo> vo = draftHeadThingService.selectPageDraftHeadThing(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 物品/材料,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadThingVo>> listDraftHeadThing(DraftHeadThingDto findDto) {
        startPage();
        List<DraftHeadThingVo> list = draftHeadThingService.selectDraftHeadThingList(findDto);
        return getDataTable(list);
    }

}
