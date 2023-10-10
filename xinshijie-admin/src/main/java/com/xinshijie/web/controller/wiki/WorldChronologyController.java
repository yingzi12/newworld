package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.wiki.domain.WorldChronology;
import com.xinshijie.wiki.dto.WorldChronologyFindDto;
import com.xinshijie.wiki.service.IWorldChronologyService;
import com.xinshijie.wiki.dto.WorldChronologyDto;
import com.xinshijie.wiki.vo.WorldChronologyVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  世界年表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Slf4j
@Tag(name = " WorldChronologyController", description = "后台- 世界年表")
@RestController
@RequestMapping("/wiki/worldChronology")
public class  WorldChronologyController  extends BaseController{

    @Autowired
    private IWorldChronologyService worldChronologyService;

    /**
    * 世界年表 查询
    * @return
    */
    @Log(title = "世界年表", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    public Result<Page<WorldChronologyVo>> select(@RequestBody WorldChronologyDto findDto){
        Page<WorldChronologyVo> vo = worldChronologyService.selectPageWorldChronology(findDto);
        return Result.success(vo);
    }

    /**
     * 查询元素列表
     */
    @Log(title = " 世界年表", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<WorldChronologyVo>> listWorldChronology(WorldChronologyFindDto findDto) {
        startPage();
        List<WorldChronologyVo> list = worldChronologyService.selectWorldChronologyList(findDto);
        return getDataTable(list);
    }

}
