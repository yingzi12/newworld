package com.xinshijie.wiki.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ChapterStatusEnums;
import com.xinshijie.common.enums.DraftStatusEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Chapter;
import com.xinshijie.wiki.domain.DraftChapter;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.mapper.DraftChapterMapper;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IChapterService;
import com.xinshijie.wiki.service.IDraftChapterService;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
public class DraftChapterServiceImpl extends ServiceImpl<DraftChapterMapper, DraftChapter> implements IDraftChapterService {

    @Autowired
    private DraftChapterMapper draftchapterMapper;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询故事章节
     */
    @Override
    public List<DraftChapterVo> selectDraftChapterList(DraftChapterFindDto dto) {
        return draftchapterMapper.selectDraftChapterList(dto);
    }

    /**
     * 分页查询故事章节
     */
    @Override
    public Page<DraftChapterVo> selectPageDraftChapter(DraftChapterDto dto) {
        Page<DraftChapterVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return draftchapterMapper.selectPageDraftChapter(page, dto);
    }

    /**
     * 分页查询故事章节
     */
    @Override
    public Page<DraftChapterVo> getPageDraftChapter(DraftChapterDto dto) {
        Page<DraftChapterVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DraftChapterVo> qw = new QueryWrapper<>();
        return draftchapterMapper.getPageDraftChapter(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftChapter add(ChapterAddDto dto) {
        StoryVo storyVo = storyService.getInfo(dto.getSid());
        if (storyVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_STORY_DOES_NOT_EXIST );
        }
        DraftChapter draftChapter = new DraftChapter();
        BeanUtils.copyProperties(dto, draftChapter);
        if (dto.getIsNew() == 2) {
            ChapterVo chapterVo = chapterService.getInfo(dto.getSid(), dto.getId());
            if (chapterVo == null) {
                throw new ServiceException(ResultCodeEnum.CHAPTER_DOES_NOT_EXIST);
            }
            dto.setPid(chapterVo.getPid());
            draftChapter.setSourceScid(chapterVo.getId());
        }
        ChapterVo reelVo = chapterService.getInfo(dto.getSid(), dto.getPid());
        if (reelVo == null) {
            throw new ServiceException(ResultCodeEnum.DIRECTORY_DOES_NOT_EXIST );
        }
        draftChapter.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        draftChapter.setWid(storyVo.getWid());
        draftChapter.setSid(storyVo.getId());
        draftChapter.setSname(storyVo.getName());
        draftChapter.setLevel(1);
        draftChapter.setIsNew(dto.getIsNew());
        draftChapter.setPname(reelVo.getTitle());
        draftChapter.setPid(reelVo.getId());
        draftchapterMapper.insert(draftChapter);
        return draftChapter;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ChapterUpdateDto dto) {
        DraftChapterDto draftChapter = new DraftChapterDto();
        BeanUtils.copyProperties(dto, draftChapter);
        return draftchapterMapper.edit(draftChapter);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long sid, Long dscid) {
        return draftchapterMapper.delById(sid, dscid);
    }

    /**
     * 根据id数据
     */
    @Override
    public DraftChapterVo selectByIdSidUserId(Long sid, Long dscid, Long userId) {
        return draftchapterMapper.selectByIdSidUserId(sid, dscid, userId);
    }

    @Override
    public DraftChapterVo getInfo(Long dscid) {
        return draftchapterMapper.getInfo(dscid);
    }

    @Override
    public int audit(ChapterAuditDto dto) {
        authorService.isCheck(dto.getSid());

        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        DraftChapterVo draftChapterVo = selectByIdSidUserId(dto.getSid(), dto.getDscid(), null);

        DraftChapterDto chapterDto = new DraftChapterDto();
        chapterDto.setId(draftChapterVo.getId());
        chapterDto.setSid(draftChapterVo.getSid());
        chapterDto.setAuditNumber(dto.getAuditNumber());
        chapterDto.setAuditContent(dto.getAuditContent());
        chapterDto.setAuditId(userid);
        chapterDto.setAuditName(username);
        chapterDto.setAuditTime(LocalDateTime.now());

        //通过审核
        if (1 == dto.getAuditStatus()) {
            chapterDto.setStatus(DraftStatusEnums.AUDITYES.getCode());
            updateChapter(draftChapterVo);
        } else {
            chapterDto.setStatus(DraftStatusEnums.AUDITNO.getCode());
        }
        //更新草稿状态
        draftchapterMapper.edit(chapterDto);
        storyService.updateCountAuditChapter(dto.getSid(),-1);

        return 0;
    }

    /**
     * 更新章节
     *
     * @param draftChapterVo
     */
    public void updateChapter(DraftChapterVo draftChapterVo) {
        //如果是插入 chapterVo1 id upId  downId 修改downId,id=draftChapterVo.upId,downId=chapter.id
        //  chapterVo2 id upId  downId  插入的chapter。chapter.upId=draftChapterVo.upId chapter.downId=draftChapterVo.downId
        //  chapterVo3 id upId  downId ,修改 upId,    id=draftChapterVo.downId,upId=chapter.id
        ChapterDto chapter = new ChapterDto();
        BeanUtils.copyProperties(draftChapterVo, chapter);
        chapter.setId(draftChapterVo.getSourceScid());
        chapter.setTitle(draftChapterVo.getTitle());
        chapter.setUpdateId(draftChapterVo.getCreateId());
        chapter.setUpdateTime(draftChapterVo.getCreateTime());
        chapter.setUpdateName(draftChapterVo.getCreateName());
        chapter.setPid(draftChapterVo.getPid());
        chapter.setCountWorld(draftChapterVo.getCountWorld());
        chapter.setLevel(draftChapterVo.getLevel());
        chapter.setContent(draftChapterVo.getContent());
        chapter.setUpId(draftChapterVo.getUpId());
        chapter.setDownId(draftChapterVo.getDownId());
        if (draftChapterVo.getIsNew() == 1) {
            chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
            chapter.setCreateId(draftChapterVo.getCreateId());
            chapter.setCreateTime(draftChapterVo.getCreateTime());
            chapter.setCreateName(draftChapterVo.getCreateName());
            Chapter c = chapterService.add(chapter);
            chapter.setId(c.getId());
        } else {
            //3表示插入
            if (draftChapterVo.getIsNew() == 3) {
                chapter.setSerialNumber(draftChapterVo.getSerialNumber());
                chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
                chapter.setCreateId(draftChapterVo.getCreateId());
                chapter.setCreateTime(draftChapterVo.getCreateTime());
                chapter.setCreateName(draftChapterVo.getCreateName());
                Chapter c = chapterService.add(chapter);
                chapterService.updateUpidDownId(draftChapterVo.getSid(),draftChapterVo.getWid(),draftChapterVo.getUpId(),null,chapter.getDownId());
                chapterService.updateUpidDownId(draftChapterVo.getSid(),draftChapterVo.getWid(),draftChapterVo.getDownId(),c.getUpdateId(),null);
                chapter.setId(c.getId());
            }else {
                chapter.setStatus(null);
                chapterService.edit(chapter);
            }
        }
        updateSotry(chapter);
    }

    //更新故事
    public void updateSotry(ChapterDto dto) {
        StoryDto storyDto = new StoryDto();
        storyDto.setId(dto.getSid());
        storyDto.setUpdateChapterId(dto.getId());
        storyDto.setUpdateChapter(dto.getTitle());
        storyDto.setUpdateChapterTime(LocalDateTime.now());
        storyDto.setUpdateId(dto.getUpdateId());
        storyDto.setUpdateTime(dto.getUpdateTime());
        storyDto.setUpdateName(dto.getUpdateName());
        storyService.edit(storyDto);
    }

    @Override
    public Integer issue(Long sid, Long dscid) {
        Long userid = SecurityUtils.getUserId();
        DraftChapterVo draftChapterVo = selectByIdSidUserId(sid, dscid, userid);
        if (draftChapterVo == null || draftChapterVo.getCreateId() != userid) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (!draftChapterVo.getStatus().equals(DraftStatusEnums.DRAFT.getCode())) {
            throw new ServiceException(ResultCodeEnum.ABNORMAL_STATUS);
        }
        AuthorVo authorVo = authorService.selectBySidUserId(sid, userid);
        if (authorVo != null) {
            //如果是管理员,直接通过审核
            ChapterAuditDto chapterAuditDto = new ChapterAuditDto();
            chapterAuditDto.setSid(sid);
            chapterAuditDto.setDscid(dscid);
            chapterAuditDto.setAuditStatus(1);
            chapterAuditDto.setAuditNumber(0);
            chapterAuditDto.setAuditContent("管理员自动审核");
            return audit(chapterAuditDto);
        } else {
            DraftChapterDto chapter = new DraftChapterDto();
            chapter.setCreateTime(LocalDateTime.now());
            chapter.setId(dscid);
            chapter.setSid(sid);
            chapter.setStatus(DraftStatusEnums.ISSUE.getCode());
            chapter.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
            storyService.updateCountAuditChapter(sid,1);
            return draftchapterMapper.edit(chapter);
        }
    }

    @Override
    public int issueClose(Long sid, Long dscid) {
        Long userid = SecurityUtils.getUserId();
        DraftChapterVo draftChapterVo = selectByIdSidUserId(sid, dscid, userid);
        if (draftChapterVo == null || draftChapterVo.getCreateId() != userid) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (!draftChapterVo.getStatus().equals(DraftStatusEnums.SAVE.getCode())) {
            throw new ServiceException(ResultCodeEnum.ABNORMAL_STATUS);
        }
        DraftChapterDto chapter = new DraftChapterDto();
        chapter.setId(dscid);
        chapter.setSid(sid);
        chapter.setStatus(DraftStatusEnums.SAVE.getCode());
        storyService.updateCountAuditChapter(sid,-1);
        return draftchapterMapper.edit(chapter);
    }

    @Override
    public int autorIssNo(Long sid, Long dscid) {
        DraftChapterDto chapter = new DraftChapterDto();
        chapter.setId(dscid);
        chapter.setSid(sid);
        chapter.setStatus(DraftStatusEnums.AUTORISSOR.getCode());
        chapter.setAuditNumber(DraftStatusEnums.AUTORISSOR.getCode());
        chapter.setAuditContent(DraftStatusEnums.AUTORISSOR.getName());
        storyService.updateCountAuditChapter(sid,-1);
        return draftchapterMapper.edit(chapter);
    }

    @Override
    public int autorAuditYes(Long sid, Long dscid) {
        Long userid = 0L;
        String username = "系统自动";
        DraftChapterVo draftChapterVo = selectByIdSidUserId(sid, dscid, null);

        DraftChapterDto chapterDto = new DraftChapterDto();
        chapterDto.setId(draftChapterVo.getId());
        chapterDto.setSid(draftChapterVo.getSid());
        chapterDto.setAuditNumber(DraftStatusEnums.AUTORAUDIT.getCode());
        chapterDto.setAuditContent(DraftStatusEnums.AUTORAUDIT.getName());
        chapterDto.setAuditId(userid);
        chapterDto.setAuditName(username);
        chapterDto.setAuditTime(LocalDateTime.now());
        draftChapterVo.setStatus(DraftStatusEnums.AUDITYES.getCode());
        updateChapter(draftChapterVo);
        storyService.updateCountAuditChapter(draftChapterVo.getSid(),-1);
        //更新草稿状态
        draftchapterMapper.edit(chapterDto);
        return 0;
    }


    @Override
    public int updateStatus(DraftChapterDto dto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        dto.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setUpdateTime(LocalDateTime.now());
        return draftchapterMapper.updateStatus(dto);
    }


    @Override
    public int autorUpdateIss() {
        Integer count=draftchapterMapper.autorUpdateIss(DraftStatusEnums.DRAFT.getCode(), DraftStatusEnums.AUTORISSOR.getCode());
        return count;
    }

    @Override
    public List<DraftChapterVo> findOvertimeAudit(Long size) {
        return draftchapterMapper.findOvertimeAudit(size);
    }

    /**
     * 更新入库
     *
     * @return
     */
    public void updateStorage(Long sid, Long dscid) {
        DraftChapterVo chapterVo = draftchapterMapper.getInfo(dscid);
        if (chapterVo == null && !chapterVo.getSid().equals(sid)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
    }
}

