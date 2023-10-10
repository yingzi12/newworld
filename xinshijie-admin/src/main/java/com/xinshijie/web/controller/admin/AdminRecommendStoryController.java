package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.RecommendStory;
import com.xinshijie.wiki.dto.RecommendStoryAddDto;
import com.xinshijie.wiki.dto.RecommendStoryDto;
import com.xinshijie.wiki.service.IRecommendStoryService;
import com.xinshijie.wiki.vo.RecommendStoryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  推荐的小说，首页使用 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " AdminRecommendStoryController", description = "推荐的小说，首页使用")
@RestController
@RequestMapping("/admin/RecommendStory")
public class AdminRecommendStoryController extends BaseController{

    @Autowired
    private IRecommendStoryService recommendStoryService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<RecommendStory> add(@RequestBody RecommendStoryAddDto dto){
        RecommendStory vo = recommendStoryService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = recommendStoryService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody RecommendStoryDto dto){
        Integer  vo = recommendStoryService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<RecommendStoryVo> getInfo(@PathVariable("id") Long id) {
        RecommendStoryVo vo = recommendStoryService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "推荐的小说，首页使用", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<RecommendStoryVo>> select(@RequestBody RecommendStoryDto findDto){
        Page<RecommendStoryVo> vo = recommendStoryService.selectPageRecommendStory(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<RecommendStoryVo>> listRecommendStory(RecommendStoryDto findDto) {
        startPage();
        List<RecommendStoryVo> list = recommendStoryService.selectRecommendStoryList(findDto);
        return getDataTable(list);
    }

}
