package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementHeadRace;
import com.xinshijie.wiki.service.IElementHeadRaceService;
import com.xinshijie.wiki.dto.ElementHeadRaceDto;
import com.xinshijie.wiki.vo.ElementHeadRaceVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  种族,元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " ElementHeadRaceController", description = "后台- 种族,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/elementHeadRace")
public class  ElementHeadRaceController  extends BaseController{

    @Autowired
    private IElementHeadRaceService elementHeadRaceService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementHeadRace> add(@RequestBody  ElementHeadRaceDto dto){
        ElementHeadRace vo = elementHeadRaceService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = elementHeadRaceService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementHeadRaceDto dto){
        Integer  vo = elementHeadRaceService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementHeadRaceVo> getInfo(@PathVariable("id") Long id) {
        ElementHeadRaceVo vo = elementHeadRaceService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<ElementHeadRaceVo>> select(@RequestBody ElementHeadRaceDto findDto){
        Page<ElementHeadRaceVo> vo = elementHeadRaceService.selectPageElementHeadRace(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<ElementHeadRaceVo>> listElementHeadRace(ElementHeadRaceDto findDto) {
        startPage();
        List<ElementHeadRaceVo> list = elementHeadRaceService.selectElementHeadRaceList(findDto);
        return getDataTable(list);
    }

}
