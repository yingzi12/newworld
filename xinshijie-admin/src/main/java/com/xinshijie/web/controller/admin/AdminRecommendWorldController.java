package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.RecommendWorld;
import com.xinshijie.wiki.dto.RecommendWorldAddDto;
import com.xinshijie.wiki.dto.RecommendWorldDto;
import com.xinshijie.wiki.service.IRecommendWorldService;
import com.xinshijie.wiki.vo.RecommendWorldVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  推荐的世界，首页使用 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " AdminRecommendWorldController", description = "推荐的世界，首页使用")
@RestController
@RequestMapping("/admin/RecommendWorld")
public class AdminRecommendWorldController extends BaseController{

    @Autowired
    private IRecommendWorldService recommendWorldService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<RecommendWorld> add(@RequestBody RecommendWorldAddDto dto){
        RecommendWorld vo = recommendWorldService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = recommendWorldService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody RecommendWorldDto dto){
        Integer  vo = recommendWorldService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<RecommendWorldVo> getInfo(@PathVariable("id") Long id) {
        RecommendWorldVo vo = recommendWorldService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "推荐的世界，首页使用", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<RecommendWorldVo>> select(@RequestBody RecommendWorldDto findDto){
        Page<RecommendWorldVo> vo = recommendWorldService.selectPageRecommendWorld(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<RecommendWorldVo>> listRecommendWorld(RecommendWorldDto findDto) {
        startPage();
        List<RecommendWorldVo> list = recommendWorldService.selectRecommendWorldList(findDto);
        return getDataTable(list);
    }

}
