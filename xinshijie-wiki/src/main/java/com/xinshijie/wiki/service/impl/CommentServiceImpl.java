package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.UserOptionEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Comment;
import com.xinshijie.wiki.domain.DiscussComment;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.mapper.CommentMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IOperationCountService countService;

    @Autowired
    private IWorldService worldService;

    @Autowired
    private IStoryService storyService;

    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询评论表
     */
    @Override
    public List<CommentVo> selectCommentList(CommentFindDto dto) {
        return commentMapper.selectCommentList(dto);
    }

    /**
     * 分页查询评论表
     */
    @Override
    public Page<CommentVo> selectPageComment(CommentDto dto) {
        Page<CommentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return commentMapper.selectPageComment(page, dto);
    }

    /**
     * 分页查询评论表
     */
    @Override
    public Page<CommentVo> getPageComment(CommentDto dto) {
        Page<CommentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<CommentVo> qw = new QueryWrapper<>();
        return commentMapper.getPageComment(page, qw);
    }

    @Override
    public List<CommentVo> selectCommentByWorld(CommentFindDto findDto) {
        return commentMapper.selectCommentList(findDto);
    }

    @Override
    public List<CommentVo> selectCommentByStory(CommentFindDto findDto) {
        return commentMapper.selectCommentList(findDto);
    }

    /**
     * 新增数据
     */
    @Override
    public Comment add(CommentAddDto addDto) {
        Comment dto=new Comment();
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        WorldVo world = worldService.getInfo(addDto.getWid());
        if (world == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST);
        }
        if (addDto.getSource() ==2) {
            StoryVo storyVo = storyService.getInfo(addDto.getSid());
            if (storyVo == null) {
                throw new ServiceException(ResultCodeEnum.THE_STORY_DOES_NOT_EXIST);
            }
            dto.setSid(storyVo.getId());
            dto.setSname(storyVo.getName());

        }
        redidentService.add(addDto.getWid());
        countService.updateCount(addDto.getWid(), userid, UserOptionEnums.ADD_COMMENT);
        dto.setWid(addDto.getWid());
        dto.setSource(addDto.getSource());
        dto.setComment(addDto.getComment());
        dto.setUpid(0L);
        dto.setRanks(0);
        dto.setStatus(0);
        dto.setTypes(0);
        dto.setPid(0L);
        dto.setWname(world.getName());
        dto.setCreateName(username);
        dto.setCreateId(userid);
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setNickname(SecurityUtils.getNickName());
        dto.setCircleUrl(SecurityUtils.getLoginUser().getAvatar());
        commentMapper.insert(dto);
        return dto;
    }

    /**
     * 新增数据
     */
    @Override
    public Comment reply(CommentReplyAddDto addDto) {
        Comment dto=new Comment();
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();

        WorldVo world = worldService.getInfo(addDto.getWid());
        if (world == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST);
        }

        if (addDto.getSource() ==2) {
            StoryVo storyVo = storyService.getInfo(addDto.getSid());
            if (storyVo == null) {
                throw new ServiceException(ResultCodeEnum.THE_STORY_DOES_NOT_EXIST);
            }
            dto.setSid(storyVo.getId());
            dto.setSname(storyVo.getName());

        }
        CommentVo commentVo = commentMapper.getInfo(addDto.getUpid());
        if (commentVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_COMMIT_DOES_NOT_EXIST);
        }
        dto.setWid(addDto.getWid());
        dto.setSid(addDto.getSid());
        dto.setUpid(commentVo.getId());
        dto.setPid(commentVo.getPid());
        dto.setRanks(commentVo.getRanks()+1);
        dto.setStatus(1);
        dto.setTypes(1);
        dto.setCreateName(username);
        dto.setCreateId(userid);
        dto.setReply(commentVo.getComment());
        dto.setReplyNickname(commentVo.getNickname());
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setNickname(SecurityUtils.getNickName());
        dto.setCircleUrl(SecurityUtils.getLoginUser().getAvatar());
        dto.setComment(addDto.getComment());
        dto.setReplyUserId(commentVo.getCreateId());
        updateCountReply(addDto.getUpid());
        updateCountReply(commentVo.getPid());
        redidentService.add(dto.getWid());
        countService.updateCount(dto.getWid(), userid, UserOptionEnums.REPLY_COMMENT);
        commentMapper.insert(dto);
        return dto;
    }

    public Integer  updateCountReply(Long id){
        return commentMapper.updateCountReply(id,1);
    }
    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(CommentDto dto) {
        return commentMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return commentMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public CommentVo getInfo(Long id) {
        return commentMapper.getInfo(id);
    }

    @Override
    public Integer like(Long id, Long userid) {
        return null;
    }

    @Override
    public Integer disagree(Long id, Long userid) {
        return null;
    }
}
