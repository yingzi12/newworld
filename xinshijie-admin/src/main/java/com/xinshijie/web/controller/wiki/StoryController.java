package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.StatusEnums;
import com.xinshijie.common.enums.StoryStatusEnums;
import com.xinshijie.common.enums.TypeEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.dto.StoryFindDto;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.vo.StoryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 故事 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Slf4j
@RestController
@Tag(name = "StoryController", description = "故事")
@RequestMapping("/wiki/story")
public class StoryController extends BaseController {
    @Autowired
    private IStoryService storyService;


    /**
     * 查询世界列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<StoryVo>> list(StoryFindDto dto) {
        dto.setStatus(StoryStatusEnums.NORMAL.getCode());
        dto.setIsPrivate(StatusEnums.NO.getCode());

        // 获取当前用户ID
        startPage();
        List<StoryVo> list = storyService.selectStoryList(dto);
        return getDataTable(list);
    }

    /**
     * 获取世界详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<StoryVo> getInfo(@PathVariable("id") Long id) {
        StoryVo story = storyService.getInfo(id);
        if (story.getStatus() == StoryStatusEnums.NORMAL.getCode()) {

        }
        if (story.getIsPrivate() != StatusEnums.NO.getCode()) {
            throw new ServiceException(ResultCodeEnum.STORY_IS_PRIVE);
        }
        story.setTypeName(TypeEnums.getNameByCode(story.getTypes()));

        return Result.success(story);
    }

}

