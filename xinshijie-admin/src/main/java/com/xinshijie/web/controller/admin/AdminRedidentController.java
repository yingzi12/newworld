package com.xinshijie.web.controller.admin;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.wiki.dto.RedidentDto;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.RedidentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 管理员数Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "AdminRedidentController", description = "后台-居民")
@RequestMapping("/admin/redident")
public class AdminRedidentController extends BaseController {
    @Autowired
    private IRedidentService redidentService;
    @Autowired
    private IManageService manageService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询居民数", description = "查询居民数")
    public TableDataInfo<List<RedidentVo>> list(RedidentDto dto) {
        manageService.isCheck(dto.getWid());
        startPage();
        List<RedidentVo> list = redidentService.selectRedidentList(dto);
        return getDataTable(list);
    }

}
