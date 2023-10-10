package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Bookshelf;
import com.xinshijie.wiki.dto.BookshelfDto;
import com.xinshijie.wiki.service.IBookshelfService;
import com.xinshijie.wiki.vo.BookshelfVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 书架 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Slf4j
@RestController
@Tag(name = "BookshelfController", description = "书架")
@RequestMapping("/wiki/bookshelf")
public class BookshelfController extends BaseController {
    @Autowired
    private IBookshelfService bookshelfService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询书架", description = "查询书架")
    public Result<List<BookshelfVo>> list(@RequestBody BookshelfDto dto) {
        List<BookshelfVo> values = bookshelfService.selectBookshelfList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询书架", description = "分页查询书架")
    public Result<Page<BookshelfVo>> selectSysRoleList(@RequestBody BookshelfDto dto) {
        Page<BookshelfVo> values = bookshelfService.selectPageBookshelf(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增书架", description = "新增书架")
    public Result<Bookshelf> add(@RequestBody BookshelfDto dto) {
        return Result.success(bookshelfService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody BookshelfDto dto) {
        return Result.success(bookshelfService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(bookshelfService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<BookshelfVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(bookshelfService.getInfo(id));
    }
}

