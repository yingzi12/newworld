package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Image;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.service.IImageService;
import com.xinshijie.wiki.vo.ImageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 图片信息表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@RestController
@Tag(name = "ImageController", description = "图片信息表")
@RequestMapping("/wiki/image")
public class ImageController extends BaseController {
    @Autowired
    private IImageService imageService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询图片信息表", description = "查询图片信息表")
    public Result<List<ImageVo>> list(@RequestBody ImageDto dto) {
        List<ImageVo> values = imageService.selectImageList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询图片信息表", description = "分页查询图片信息表")
    public Result<Page<ImageVo>> selectSysRoleList(@RequestBody ImageDto dto) {
        Page<ImageVo> values = imageService.selectPageImage(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增图片信息表", description = "新增图片信息表")
    public Result<Image> add(@RequestBody ImageDto dto) {
        return Result.success(imageService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody ImageDto dto) {
        return Result.success(imageService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(imageService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<ImageVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(imageService.getInfo(id));
    }
}

