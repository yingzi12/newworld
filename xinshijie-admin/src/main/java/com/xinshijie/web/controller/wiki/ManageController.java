package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.wiki.dto.ManageFindDto;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.vo.ManageVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Tag(name = "ManageController", description = "管理员")
@RequestMapping("/wiki/manage")
public class ManageController extends BaseController {
    @Autowired
    private IManageService manageService;

    /**
     * 查询管理员数列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<ManageVo>> list(ManageFindDto redident) {
        startPage();
        List<ManageVo> list = manageService.selectManageList(redident);
        return getDataTable(list);
    }


    /**
     * 查询世界评论列表
     */
    @GetMapping("/getWorldManage")
    public TableDataInfo<List<ManageVo>> getWorldManage(@Validated @NotNull(message = "缺少必须的参数") @Param("wid") Integer wid) {
        List<ManageVo> list = manageService.selectManageByWid(wid);
        return getDataTable(list);
    }


}
