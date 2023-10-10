package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.LookingBooks;
import com.xinshijie.wiki.service.ILookingBooksService;
import com.xinshijie.wiki.dto.LookingBooksDto;
import com.xinshijie.wiki.vo.LookingBooksVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  寻找书 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " LookingBooksController", description = "后台- 寻找书")
@RestController
@RequestMapping("/admin/LookingBooks")
public class AdminLookingBooksController extends BaseController{

    @Autowired
    private ILookingBooksService lookingBooksService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 寻找书", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<LookingBooks> add(@RequestBody  LookingBooksDto dto){
        LookingBooks vo = lookingBooksService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 寻找书", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = lookingBooksService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 寻找书", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody LookingBooksDto dto){
        Integer  vo = lookingBooksService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 寻找书", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<LookingBooksVo> getInfo(@PathVariable("id") Long id) {
        LookingBooksVo vo = lookingBooksService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "寻找书", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<LookingBooksVo>> select(@RequestBody LookingBooksDto findDto){
        Page<LookingBooksVo> vo = lookingBooksService.selectPageLookingBooks(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 寻找书", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<LookingBooksVo>> listLookingBooks(LookingBooksDto findDto) {
        startPage();
        List<LookingBooksVo> list = lookingBooksService.selectLookingBooksList(findDto);
        return getDataTable(list);
    }

}
