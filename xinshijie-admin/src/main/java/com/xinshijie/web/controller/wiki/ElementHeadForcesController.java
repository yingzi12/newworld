package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementHeadForces;
import com.xinshijie.wiki.service.IElementHeadForcesService;
import com.xinshijie.wiki.dto.ElementHeadForcesDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  势力.元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " ElementHeadForcesController", description = "后台- 势力.元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/elementHeadForces")
public class  ElementHeadForcesController  extends BaseController{

    @Autowired
    private IElementHeadForcesService elementHeadForcesService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementHeadForces> add(@RequestBody  ElementHeadForcesDto dto){
        ElementHeadForces vo = elementHeadForcesService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = elementHeadForcesService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementHeadForcesDto dto){
        Integer  vo = elementHeadForcesService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementHeadForcesVo> getInfo(@PathVariable("id") Long id) {
        ElementHeadForcesVo vo = elementHeadForcesService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<ElementHeadForcesVo>> select(@RequestBody ElementHeadForcesDto findDto){
        Page<ElementHeadForcesVo> vo = elementHeadForcesService.selectPageElementHeadForces(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<ElementHeadForcesVo>> listElementHeadForces(ElementHeadForcesDto findDto) {
        startPage();
        List<ElementHeadForcesVo> list = elementHeadForcesService.selectElementHeadForcesList(findDto);
        return getDataTable(list);
    }

}
