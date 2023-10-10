package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.*;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.Chapter;
import com.xinshijie.wiki.dto.ChapterDto;
import com.xinshijie.wiki.dto.ChapterFindDto;
import com.xinshijie.wiki.mapper.ChapterMapper;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IChapterService;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.vo.ChapterIntroVo;
import com.xinshijie.wiki.vo.ChapterNameVo;
import com.xinshijie.wiki.vo.ChapterVo;
import com.xinshijie.wiki.vo.StoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 故事章节 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Slf4j
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private IStoryService storyService;

    @Autowired
    private IAuthorService authorService;

    /**
     * 查询故事章节
     */
    @Override
    public List<ChapterVo> selectChapterList(ChapterFindDto dto) {
        return chapterMapper.selectChapterList(dto);
    }

    @Override
    public List<ChapterNameVo> selectChapterNameList(ChapterFindDto dto) {
        return chapterMapper.selectChapterNameList(dto);
    }

    /**
     * 分页查询故事章节
     */
    @Override
    public Page<ChapterVo> selectPageChapter(ChapterDto dto) {
        Page<ChapterVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return chapterMapper.selectPageChapter(page, dto);
    }

    /**
     * 分页查询故事章节
     */
    @Override
    public Page<ChapterVo> getPageChapter(ChapterDto dto) {
        Page<ChapterVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ChapterVo> qw = new QueryWrapper<>();
        return chapterMapper.getPageChapter(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Chapter add(ChapterDto dto) {
        authorService.isCheck(dto.getSid());

        StoryVo storyVo = storyService.getInfo(dto.getSid());
        if (storyVo == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(dto, chapter);
        chapter.setWid(storyVo.getWid());
        chapter.setSname(storyVo.getName());
        chapterMapper.insert(chapter);
        return chapter;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ChapterDto dto) {
        authorService.isCheck(dto.getSid());

        return chapterMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long sid, Long scid) {
        authorService.isCheck(sid);

        return chapterMapper.delById(sid, scid);
    }

    /**
     * 根据id数据
     */
    @Override
    public ChapterVo getInfo(Long sid, Long scid) {
        ChapterVo chapterVo = chapterMapper.getInfo(sid, scid);
//        if (chapterVo != null) {
//            ChapterVo previous = chapterMapper.previousChapter(chapterVo.getUpId(), chapterVo.getWid(), sid);
//            ChapterVo next = chapterMapper.nextChapter(chapterVo.getDownId(), chapterVo.getWid(), sid);
//            chapterVo.setPrevious(previous);
//            chapterVo.setNext(next);
//        }
        return chapterVo;
    }

    @Override
    public ChapterIntroVo getInfoIntro(Long sid, Long scid) {
        ChapterIntroVo chapterVo = chapterMapper.getInfoIntro(sid, scid);
        if (chapterVo != null) {
            ChapterIntroVo previous = chapterMapper.previousChapter(chapterVo.getUpId(), chapterVo.getWid(), sid);
            ChapterIntroVo next = chapterMapper.nextChapter(chapterVo.getDownId(), chapterVo.getWid(), sid);
            chapterVo.setPrevious(previous);
            chapterVo.setNext(next);
        }
        return chapterVo;
    }

    @Override
    public Integer like(Long id, Long userid) {

        return null;
    }

    @Override
    public Integer disagree(Long id, Long userid) {
        return null;
    }

    @Override
    public Integer see(Long id, Long userid) {
        return null;
    }

    @Override
    public ChapterIntroVo previousChapter(Long scid, Integer wid, Long sid) {
        return chapterMapper.previousChapter(scid, wid, sid);
    }

    @Override
    public ChapterIntroVo nextChapter(Long scid, Integer wid, Long sid) {
        return chapterMapper.nextChapter(scid, wid, sid);
    }

    @Override
    public Integer updateUpidDownId(Long sid, Integer wid, Long id, Long upId, Long downId) {
        return chapterMapper.updateUpidDownId(sid,wid,id,upId,downId);
    }

    @Override
    public ChapterVo selectSidWidSerialNumber(Long sid, Integer wid, Long serialNumber) {
        return chapterMapper.selectSidWidSerialNumber(sid,wid,serialNumber);
    }

}
