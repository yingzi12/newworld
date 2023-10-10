package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.dto.ChapterFindDto;
import com.xinshijie.wiki.service.IChapterService;
import com.xinshijie.wiki.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 故事章节 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Slf4j
@RestController
@Tag(name = "ChapterController", description = "故事章节")
@RequestMapping("/wiki/chapter")
public class ChapterController extends BaseController {
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询所有的章节,需要管理员权限
     */
    @GetMapping("/list")
    @Operation(summary = "查询故事章节", description = "查询故事章节")
    public TableDataInfo<List<AuthorVo>> list(ChapterFindDto dto) {
        startPage();
        List<ChapterVo> list = chapterService.selectChapterList(dto);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    @Operation(summary = "查询故事章节", description = "查询故事章节")
    public Result<List<ChapterReelVo>> listAll(ChapterFindDto dto) {
        List<ChapterNameVo> list = chapterService.selectChapterNameList(dto);
        List<ChapterReelVo> reelVoList = new ArrayList<>();
        Map<Long, List<ChapterNameVo>> chapterMap = new HashMap<>();
        for (ChapterNameVo vo : list) {
            if (vo.getPid() == 0) {
                ChapterReelVo reelVo = new ChapterReelVo();
                BeanUtils.copyProperties(vo, reelVo);
                reelVoList.add(reelVo);
            } else {
                if (chapterMap.containsKey(vo.getPid())) {
                    List<ChapterNameVo> voList = chapterMap.get(vo.getPid());
                    voList.add(vo);
                    chapterMap.put(vo.getPid(), voList);
                } else {
                    List<ChapterNameVo> voList = new ArrayList<>();
                    voList.add(vo);
                    chapterMap.put(vo.getPid(), voList);
                }
            }
        }
        for (ChapterReelVo reelVo : reelVoList) {
            reelVo.setChapterList(chapterMap.get(reelVo.getId()));
        }
        return Result.success(reelVoList);
    }


    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<ChapterIntroVo> getInfo(@RequestParam("sid") Long sid, @RequestParam("scid") Long scid) {
        return Result.success(chapterService.getInfoIntro(sid, scid));
    }
}

