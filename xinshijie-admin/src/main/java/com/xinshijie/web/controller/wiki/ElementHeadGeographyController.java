package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementHeadGeography;
import com.xinshijie.wiki.service.IElementHeadGeographyService;
import com.xinshijie.wiki.dto.ElementHeadGeographyDto;
import com.xinshijie.wiki.vo.ElementHeadGeographyVo;
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
@Tag(name = " ElementHeadGeographyController", description = "后台- 地理,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/elementHeadGeography")
public class  ElementHeadGeographyController  extends BaseController{

    @Autowired
    private IElementHeadGeographyService elementHeadGeographyService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementHeadGeography> add(@RequestBody  ElementHeadGeographyDto dto){
        ElementHeadGeography vo = elementHeadGeographyService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = elementHeadGeographyService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementHeadGeographyDto dto){
        Integer  vo = elementHeadGeographyService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementHeadGeographyVo> getInfo(@PathVariable("id") Long id) {
        ElementHeadGeographyVo vo = elementHeadGeographyService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<ElementHeadGeographyVo>> select(@RequestBody ElementHeadGeographyDto findDto){
        Page<ElementHeadGeographyVo> vo = elementHeadGeographyService.selectPageElementHeadGeography(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 地理,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<ElementHeadGeographyVo>> listElementHeadGeography(ElementHeadGeographyDto findDto) {
        startPage();
        List<ElementHeadGeographyVo> list = elementHeadGeographyService.selectElementHeadGeographyList(findDto);
        return getDataTable(list);
    }

}
