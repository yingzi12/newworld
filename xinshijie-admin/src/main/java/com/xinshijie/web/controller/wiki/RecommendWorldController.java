package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.RecommendEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.RecommendWorld;
import com.xinshijie.wiki.dto.RecommendStoryDto;
import com.xinshijie.wiki.service.IRecommendWorldService;
import com.xinshijie.wiki.dto.RecommendWorldDto;
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
@Tag(name = " RecommendWorldController", description = "推荐的世界，首页使用")
@RestController
@RequestMapping("/wiki/recommendWorld")
public class  RecommendWorldController  extends BaseController{

    @Autowired
    private IRecommendWorldService recommendWorldService;

    /**
     * 查询元素列表
     */
    @Log(title = " 推荐的世界，首页使用", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<RecommendWorldVo>> listRecommendWorld(Integer recType) {
        RecommendEnums enums=  RecommendEnums.getByCode(recType);
        if(enums==null){
            throw new ServiceException(ResultCodeEnum.CODE_IS_EXIST_ERROR);
        }
        RecommendWorldDto findDto=new RecommendWorldDto();
        findDto.setRecType(recType);
        findDto.setPageSize(enums.getMax()+0L);
        List<RecommendWorldVo> list = recommendWorldService.selectRecommendWorldList(findDto);
        return getDataTable(list);
    }

}
