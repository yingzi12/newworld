package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.DraftHeadRole;
import com.xinshijie.wiki.service.IDraftHeadRoleService;
import com.xinshijie.wiki.dto.DraftHeadRoleDto;
import com.xinshijie.wiki.vo.DraftHeadRoleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  角色元素头,不同的元素模板对应不同的head 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " DraftHeadRoleController", description = "后台- 角色元素头,不同的元素模板对应不同的head")
@RestController
@RequestMapping("/DraftHeadRole")
public class  DraftHeadRoleController  extends BaseController{

    @Autowired
    private IDraftHeadRoleService draftHeadRoleService;

    /**
    * 世界年表 添加
    * @return
    */
    @Log(title = " 角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<DraftHeadRole> add(@RequestBody  DraftHeadRoleDto dto){
        DraftHeadRole vo = draftHeadRoleService.add(dto);
        return Result.success(vo);
    }

    /**
    *  世界年表 删除
    * @return
    */
    @Log(title = " 角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    public Result<Integer> del(@PathVariable("id")  Long id){
        Integer  vo = draftHeadRoleService.delById(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 修改
    * @return
    */
    @Log(title = " 角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public Result<Integer> edit(@RequestBody DraftHeadRoleDto dto){
        Integer  vo = draftHeadRoleService.edit(dto);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询详情
    * @return
    */
    @Log(title = " 角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping(value = "/getInfo/{id}")
    public Result<DraftHeadRoleVo> getInfo(@PathVariable("id") Long id) {
        DraftHeadRoleVo vo = draftHeadRoleService.getInfo(id);
        return Result.success(vo);
    }


    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<DraftHeadRoleVo>> select(@RequestBody DraftHeadRoleDto findDto){
        Page<DraftHeadRoleVo> vo = draftHeadRoleService.selectPageDraftHeadRole(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 角色元素头,不同的元素模板对应不同的head", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<DraftHeadRoleVo>> listDraftHeadRole(DraftHeadRoleDto findDto) {
        startPage();
        List<DraftHeadRoleVo> list = draftHeadRoleService.selectDraftHeadRoleList(findDto);
        return getDataTable(list);
    }

}
