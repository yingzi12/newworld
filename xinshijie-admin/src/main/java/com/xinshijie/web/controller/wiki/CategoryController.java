package com.xinshijie.web.controller.wiki;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.utils.ListToTreeUtils;
import com.xinshijie.wiki.dto.CategoryFindDto;
import com.xinshijie.wiki.service.ICategoryService;
import com.xinshijie.wiki.vo.CategorySimplyVo;
import com.xinshijie.wiki.vo.CategoryTreeVo;
import com.xinshijie.wiki.vo.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 元素分类Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Validated
@Slf4j
@RestController
@Tag(name = "CategoryController", description = "元素分类")
@RequestMapping("/wiki/category")
public class CategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询元素分类列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询元素分类列表", description = "查询元素分类列表")
    public Result<List<CategoryVo>> list(CategoryFindDto category) {
        List<CategoryVo> list = categoryService.selectCategoryList(category);
        return Result.success(list);
    }

    /**
     * 查询元素分类列表
     */
    @Operation(summary = "查询元素分类列表", description = "查询元素分类列表")
    @GetMapping("/getTree")
    public Result<JSONArray> getTree(@Validated @NotNull(message = "wid不能为null") @Param("wid") Integer wid) {
        List<CategoryTreeVo> list = categoryService.selectCategoryByWid(wid);
        JSONArray result = ListToTreeUtils.listToTree(JSONArray.parseArray(JSON.toJSONString(list)), "id", "pid", "children");
        return Result.success(result);
    }

    /**
     * 查询元素分类列表
     */
    @Operation(summary = "查询元素分类列表返回手机格式", description = "查询元素分类列表返回手机格式")
    @GetMapping("/getTreeMobile")
    public Result<JSONArray> getTreeMobile(@Validated @NotNull(message = "wid不能为null") @Param("wid") Integer wid) {
        List<CategoryTreeVo> list = categoryService.selectCategoryByWid(wid);
        JSONArray result = ListToTreeUtils.listToTreeMobile(JSONArray.parseArray(JSON.toJSONString(list)), "id", "pid", "children");
        return Result.success(result);
    }

    /**
     * 查询元素分类列表
     */
    @Operation(summary = "查询元素分类列表返回fluter格式", description = "查询元素分类列表返回fluter格式")
    @GetMapping("/getTreeFlutter")
    public Result<JSONArray> getTreeFlutter(@Validated @NotNull(message = "wid不能为null") @Param("wid") Integer wid) {
        List<CategoryTreeVo> list = categoryService.selectByWidTier(wid, 2);
        JSONArray result = ListToTreeUtils.listToTreeMobile(JSONArray.parseArray(JSON.toJSONString(list)), "id", "pid", "children");
        return Result.success(result);
    }

    /**
     * 获取元素分类详细信息
     */
    @Operation(summary = "获取元素分类详细信息", description = "获取元素分类详细信息")
    @GetMapping(value = "/getInfo/{id}")
    public Result<CategoryVo> getInfo(@PathVariable("id") Long id) {
        return Result.success(categoryService.getInfo(id));
    }

    /**
     * 获取元素分类详细信息
     */
    @Operation(summary = "统计当前世界的分类数", description = "统计当前世界的分类数")
    @GetMapping(value = "/count/{wid}")
    public Result<Long> count(@PathVariable("wid") Integer id) {
        return Result.success(categoryService.countCategory(id));
    }

    /**
     * 查询当前世界的所有分类,只返回id,label,pid
     *
     * @param wid
     * @return
     */
    @GetMapping("/listSinply")
    public Result<List<CategorySimplyVo>> listSinply(@RequestParam("wid") Integer wid) {
        List<CategorySimplyVo> list = categoryService.selectSinplyCategoryList(wid);
        return Result.success(list);
    }


    //批量查询
    @Operation(summary = "返回上级的label", description = "返回上级的label")
    @PostMapping(value = "/findAllNameList")
    public Result<List<String>> findAllNameList(@RequestBody CategoryFindDto dto) {
        return Result.success(categoryService.findAllNameList(dto.getIds()));
    }
}
