package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.ElementImg;
import com.xinshijie.wiki.service.IElementImgService;
import com.xinshijie.wiki.dto.ElementImgDto;
import com.xinshijie.wiki.vo.ElementImgVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  元素照片列表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " ElementImgController", description = "后台- 元素照片列表")
@RestController
@RequestMapping("/elementImg")
public class  ElementImgController  extends BaseController{

    @Autowired
    private IElementImgService elementImgService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 元素照片列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<ElementImg> add(@RequestBody  ElementImgDto dto){
        ElementImg vo = elementImgService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 元素照片列表", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = elementImgService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 元素照片列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody ElementImgDto dto){
        Integer  vo = elementImgService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 元素照片列表", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<ElementImgVo> getInfo(@PathVariable("id") Long id) {
        ElementImgVo vo = elementImgService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "元素照片列表", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<ElementImgVo>> select(@RequestBody ElementImgDto findDto){
        Page<ElementImgVo> vo = elementImgService.selectPageElementImg(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 元素照片列表", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<ElementImgVo>> listElementImg(ElementImgDto findDto) {
        startPage();
        List<ElementImgVo> list = elementImgService.selectElementImgList(findDto);
        return getDataTable(list);
    }

}
