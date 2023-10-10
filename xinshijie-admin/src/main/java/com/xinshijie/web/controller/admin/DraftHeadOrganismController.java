package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadOrganism;
import com.xinshijie.wiki.service.IDraftHeadOrganismService;
import com.xinshijie.wiki.dto.DraftHeadOrganismDto;
import com.xinshijie.wiki.vo.DraftHeadOrganismVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  生物,元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " DraftHeadOrganismController", description = "后台- 生物,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadOrganism")
public class  DraftHeadOrganismController  extends BaseController{

    @Autowired
    private IDraftHeadOrganismService draftHeadOrganismService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadOrganism> add(@RequestBody  DraftHeadOrganismDto dto){
        DraftHeadOrganism vo = draftHeadOrganismService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadOrganismService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadOrganismDto dto){
        Integer  vo = draftHeadOrganismService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadOrganismVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadOrganismVo vo = draftHeadOrganismService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadOrganismVo>> select(@RequestBody DraftHeadOrganismDto findDto){
        Page<DraftHeadOrganismVo> vo = draftHeadOrganismService.selectPageDraftHeadOrganism(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 生物,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadOrganismVo>> listDraftHeadOrganism(DraftHeadOrganismDto findDto) {
        startPage();
        List<DraftHeadOrganismVo> list = draftHeadOrganismService.selectDraftHeadOrganismList(findDto);
        return getDataTable(list);
    }

}
