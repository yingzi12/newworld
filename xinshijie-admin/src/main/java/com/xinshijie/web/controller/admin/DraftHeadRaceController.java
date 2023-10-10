package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadRace;
import com.xinshijie.wiki.service.IDraftHeadRaceService;
import com.xinshijie.wiki.dto.DraftHeadRaceDto;
import com.xinshijie.wiki.vo.DraftHeadRaceVo;
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
@Tag(name = " DraftHeadRaceController", description = "后台- 种族,元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadRace")
public class  DraftHeadRaceController  extends BaseController{

    @Autowired
    private IDraftHeadRaceService draftHeadRaceService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadRace> add(@RequestBody  DraftHeadRaceDto dto){
        DraftHeadRace vo = draftHeadRaceService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadRaceService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadRaceDto dto){
        Integer  vo = draftHeadRaceService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadRaceVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadRaceVo vo = draftHeadRaceService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadRaceVo>> select(@RequestBody DraftHeadRaceDto findDto){
        Page<DraftHeadRaceVo> vo = draftHeadRaceService.selectPageDraftHeadRace(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 种族,元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadRaceVo>> listDraftHeadRace(DraftHeadRaceDto findDto) {
        startPage();
        List<DraftHeadRaceVo> list = draftHeadRaceService.selectDraftHeadRaceList(findDto);
        return getDataTable(list);
    }

}
