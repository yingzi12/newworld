package com.xinshijie.wiki.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.*;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.*;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.mapper.StoryMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * <p>
 * 故事 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Slf4j
@Service
public class StoryServiceImpl extends ServiceImpl<StoryMapper, Story> implements IStoryService {

    @Autowired
    private StoryMapper storyMapper;
    @Autowired
    private IWorldService worldService;

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IManageService manageService;
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询故事
     */
    @Override
    public List<StoryVo> selectStoryList(StoryFindDto dto) {
        return storyMapper.selectStoryList(dto);
    }

    /**
     * 分页查询故事
     */
    @Override
    public Page<StoryVo> selectPageStory(StoryDto dto) {
        Page<StoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return storyMapper.selectPageStory(page, dto);
    }

    /**
     * 分页查询故事
     */
    @Override
    public Page<StoryVo> getPageStory(StoryDto dto) {
        Page<StoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<StoryVo> qw = new QueryWrapper<>();
        return storyMapper.getPageStory(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Story add(StoryAddDto addDto) {
        WorldVo worldVo = worldService.getInfo(addDto.getWid());
        if (worldVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST);
        }
        Story story = new Story();
        BeanUtils.copyProperties(addDto, story);
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

//        Long count=authorService.countBySidUserId(story.getId(),userid);
//        if(count>20){
//            throw new ServiceException("同一个故事一个人最多创建/管理20个故事");
//        }
        story.setWid(worldVo.getId());
        story.setWname(worldVo.getName());
        story.setAuthor(username);
        story.setStatus(StoryStatusEnums.DRAFT.getCode());
        story.setCreateId(userid);
        story.setCreateName(username);
        story.setCreateTime(LocalDateTime.now());

        int value = storyMapper.insert(story);
        Author author = new Author();
        author.setSid(story.getId());
        author.setSname(story.getName());
        author.setWid(worldVo.getId());
        author.setWname(worldVo.getName());
        author.setTypes(ManageTypeEnums.AUTHOR.getCode());
        author.setUserId(userid);
        author.setUserName(username);
        author.setCreateId(story.getCreateId());
        author.setCreateName(story.getCreateName());
        authorService.save(author);
        return story;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(StoryDto dto) {
        authorService.isCheck(dto.getId());
        dto.setUpdateTime(DateUtils.getNowDate());
        return storyMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return storyMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public StoryVo getInfo(Long id) {
        return storyMapper.getInfo(id);
    }


    /**
     * 删除故事信息
     *
     * @param id 故事主键
     * @return 结果
     */
    @Override
    public int deleteStoryById(Long id) {
        Long userid = SecurityUtils.getUserId();
        AuthorVo authorVo = authorService.selectBySidUserId(id, userid);
        if (authorVo == null || authorVo.getTypes() != ManageTypeEnums.AUTHOR.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }

        //判断是否存在分类
        Story story = storyMapper.selectById(id);
        if (story.getStatus() != StoryStatusEnums.DRAFT.getCode()) {
            throw new ServiceException(ResultCodeEnum.THE_STORY_HAS_BEEN_GENERATED);
        }
        QueryWrapper<Chapter> eQuery = new QueryWrapper();
        eQuery.eq("wid", id);
        Long eCount = chapterService.count(eQuery);
        //判断故事状态
        if (eCount > 0) {
            throw new ServiceException(  ResultCodeEnum.CHAPTERS_EXIST_AND_CANNOT_BE_DELETED);
        }
        return storyMapper.deleteById(id);
    }

    @Override
    public int issue(Long sid) {
//        if(storyVo !=null){
//
//        }
//        Long userid = SecurityUtils.getUserId();
        authorService.isCheck(sid);
        StoryVo storyVo = storyMapper.getInfo(sid);

        Story story = new Story();
        story.setId(sid);
        story.setStatus(StoryStatusEnums.ISSUE.getCode());
        worldService.updateCountAuditStory(storyVo.getWid(),1);
        return storyMapper.updateById(story);
    }

    @Override
    public int audit(AuditDto dto) {
        StoryVo storyVo = storyMapper.getInfo(dto.getSid());
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();

        manageService.isCheck(storyVo.getWid());

        Story story = new Story();
        story.setId(dto.getSid());
        story.setAuditTime(LocalDateTime.now());
        story.setAuditName(username);
        story.setAuditId(userid);
        story.setAuditContent(dto.getAuditContent());
        story.setAuditNumber(dto.getAuditNumber());
        if (dto.getAuditStatus() == 1) {
            story.setStatus(StoryStatusEnums.NORMAL.getCode());
        } else {
            story.setStatus(StoryStatusEnums.AUDITNO.getCode());
        }
        worldService.updateCountAuditStory(storyVo.getWid(),-1);

        return storyMapper.updateById(story);
    }

    @Override
    public void updateCount(StoryDto storyDto) {
        //更新故事
        storyMapper.updateCount(storyDto);
        StoryVo vo=storyMapper.getInfo(storyDto.getId());
        Integer upgrade= RankEnums.getRankEnums(vo.getRanks()+1).getDiffuse();
        if(vo.getDiffuse() >= upgrade ){
            storyMapper.updateRank(vo.getId(),upgrade);
        }
    }

    public void updateChapter(Long sid, String title, Long scid) {
        StoryDto storyDto = new StoryDto();
        storyDto.setId(sid);
        storyDto.setUpdateChapter(title);
        storyDto.setUpdateChapterTime(LocalDateTime.now());
        storyDto.setUpdateChapterId(scid);
        storyMapper.updateCount(storyDto);
    }

    @Override
    public List<StoryVo> findByAuthor(StoryFindDto dto) {
        return storyMapper.findByAuthor(dto);
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
    public Integer updateCountAuditDiscuss(Long sid, Integer count) {
        return storyMapper.updateCountAuditDiscuss(sid,count);
    }

    @Override
    public Integer updateCountAuditComment(Long sid, Integer count) {
        return storyMapper.updateCountAuditComment(sid,count);
    }

    @Override
    public Integer updateCountAuditChapter(Long sid, Integer count) {
        return storyMapper.updateCountAuditChapter(sid,count);
    }

    @Override
    public Integer updateCountAuditAuthor(Long sid, Integer count) {
        return storyMapper.updateCountAuditAuthor(sid,count);
    }


    @Override
    public int updateStatus(StoryDto dto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        dto.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setUpdateTime(LocalDateTime.now());
        return storyMapper.updateStatus(dto);
    }


    @Override
    public int autorUpdateIss() {
        Integer count=storyMapper.autorUpdateIss(DraftStatusEnums.DRAFT.getCode(), DraftStatusEnums.AUTORISSOR.getCode());
        return count;
    }

    @Override
    public List<StoryVo> findOvertimeAudit(Long size) {
        return storyMapper.findOvertimeAudit(size);
    }

    /**
     * 更新入库
     *
     * @return
     */
    public Integer updateStorage(Integer wid, Long sid) {
        StoryVo storyVo = storyMapper.getInfo(sid);
        if (storyVo == null && !storyVo.getWid().equals(wid)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        return updateStory(storyVo);

    }

    public Integer updateStory(StoryVo storyVo) {
        StoryDto dto=new StoryDto();
        //计算积分
        return storyMapper.edit(dto);
    }
}
