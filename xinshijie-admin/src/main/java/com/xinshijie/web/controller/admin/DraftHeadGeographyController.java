package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadGeography;
import com.xinshijie.wiki.service.IDraftHeadGeographyService;
import com.xinshijie.wiki.dto.DraftHeadGeographyDto;
import com.xinshijie.wiki.vo.DraftHeadGeographyVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  地理,元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " DraftHeadGeographyController", description = "后台- 地理,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadGeography")
public class  DraftHeadGeographyController  extends BaseController{

    @Autowired
    private IDraftHeadGeographyService draftHeadGeographyService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadGeography> add(@RequestBody  DraftHeadGeographyDto dto){
        DraftHeadGeography vo = draftHeadGeographyService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadGeographyService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadGeographyDto dto){
        Integer  vo = draftHeadGeographyService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadGeographyVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadGeographyVo vo = draftHeadGeographyService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadGeographyVo>> select(@RequestBody DraftHeadGeographyDto findDto){
        Page<DraftHeadGeographyVo> vo = draftHeadGeographyService.selectPageDraftHeadGeography(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadGeographyVo>> listDraftHeadGeography(DraftHeadGeographyDto findDto) {
        startPage();
        List<DraftHeadGeographyVo> list = draftHeadGeographyService.selectDraftHeadGeographyList(findDto);
        return getDataTable(list);
    }

}
