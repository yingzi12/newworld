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
import com.xinshijie.wiki.domain.RecommendStory;
import com.xinshijie.wiki.service.IRecommendStoryService;
import com.xinshijie.wiki.dto.RecommendStoryDto;
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
@Tag(name = " RecommendStoryController", description = "推荐的小说，首页使用")
@RestController
@RequestMapping("/wiki/recommendStory")
public class  RecommendStoryController  extends BaseController{

    @Autowired
    private IRecommendStoryService recommendStoryService;

    /**
     * 查询元素列表
     */
    @Log(title = " 推荐的小说，首页使用", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo<List<RecommendStoryVo>> listRecommendStory(Integer recType) {
        RecommendEnums enums=  RecommendEnums.getByCode(recType);
        if(enums==null){
            throw new ServiceException(ResultCodeEnum.CODE_IS_EXIST_ERROR);
        }
        RecommendStoryDto dto=new RecommendStoryDto();
        dto.setRecType(recType);
        dto.setPageSize(enums.getMax()+0L);
        List<RecommendStoryVo> list = recommendStoryService.selectRecommendStoryList(dto);
        return getDataTable(list);
    }

}
