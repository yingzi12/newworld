package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.annotation.WorldVelidate;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Category;
import com.xinshijie.wiki.dto.CategoryAddDto;
import com.xinshijie.wiki.dto.CategoryDto;
import com.xinshijie.wiki.dto.CategoryFindDto;
import com.xinshijie.wiki.dto.CategoryUpdateDto;
import com.xinshijie.wiki.service.ICategoryService;
import com.xinshijie.wiki.vo.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Tag(name = "AdminCategoryController", description = "后台-分类")
@RequestMapping("/admin/category")
public class AdminCategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询元素分类列表
     */
    @GetMapping("/list")
    public Result<List<CategoryVo>> list(CategoryFindDto category) {
        List<CategoryVo> list = categoryService.selectCategoryList(category);
        return Result.success(list);
    }


    /**
     * 获取元素分类详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<CategoryVo> getInfo(@PathVariable("id") Long id) {
        return Result.success(categoryService.getInfo(id));
    }

    /**
     * 新增元素分类
     */
    @WorldVelidate
    @Log(title = "元素分类", businessType = BusinessType.INSERT)
    @Operation(summary = "元素分类最多创建4层", description = "元素分类最多创建6层,每一层最多18条数据,最大分类数为9999")
    @PostMapping("/add")
    public Result<CategoryVo> add(@RequestBody @Validated CategoryAddDto categoryAddDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddDto, category);
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        // 获取当前的用户信息
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        System.out.println(JSON.toJSONString(loginUser));
        category.setCreateName(username);
        category.setCreateId(userid);
        category.setUpdateId(userid);
        category.setUpdateName(username);

        categoryService.insertCategory(category);
        CategoryVo vo = new CategoryVo();
        BeanUtils.copyProperties(category, vo);
        return Result.success(vo);
    }

    /**
     * 修改元素分类
     */
    @WorldVelidate
    @Log(title = "元素分类", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody CategoryDto category) {
        return toAjax(categoryService.edit(category));
    }

    /**
     * 修改元素分类名称
     */
    @WorldVelidate
    @Log(title = "元素分类", businessType = BusinessType.UPDATE)
    @PutMapping("/editName")
    public Result<String> editName(@RequestBody CategoryUpdateDto dto) {
        CategoryDto category = new CategoryDto();
        category.setId(dto.getId());
        category.setLabel(dto.getLabel());
        return toAjax(categoryService.edit(category));
    }

}
