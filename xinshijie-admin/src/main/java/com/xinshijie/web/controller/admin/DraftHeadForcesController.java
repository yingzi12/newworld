package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadForces;
import com.xinshijie.wiki.service.IDraftHeadForcesService;
import com.xinshijie.wiki.dto.DraftHeadForcesDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
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
@Tag(name = " DraftHeadForcesController", description = "后台- 势力.元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadForces")
public class  DraftHeadForcesController  extends BaseController{

    @Autowired
    private IDraftHeadForcesService draftHeadForcesService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadForces> add(@RequestBody  DraftHeadForcesDto dto){
        DraftHeadForces vo = draftHeadForcesService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadForcesService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadForcesDto dto){
        Integer  vo = draftHeadForcesService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadForcesVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadForcesVo vo = draftHeadForcesService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadForcesVo>> select(@RequestBody DraftHeadForcesDto findDto){
        Page<DraftHeadForcesVo> vo = draftHeadForcesService.selectPageDraftHeadForces(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 势力.元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadForcesVo>> listDraftHeadForces(DraftHeadForcesDto findDto) {
        startPage();
        List<DraftHeadForcesVo> list = draftHeadForcesService.selectDraftHeadForcesList(findDto);
        return getDataTable(list);
    }

}
