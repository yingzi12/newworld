package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.UserOptionEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.DiscussComment;
import com.xinshijie.wiki.dto.DiscussCommentAddDto;
import com.xinshijie.wiki.dto.DiscussCommentDto;
import com.xinshijie.wiki.dto.DiscussCommentReplyAddDto;
import com.xinshijie.wiki.mapper.DiscussCommentMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.DiscussCommentVo;
import com.xinshijie.wiki.vo.DiscussVo;
import com.xinshijie.wiki.vo.StoryVo;
import com.xinshijie.wiki.vo.WorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 讨论回复表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class DiscussCommentServiceImpl extends ServiceImpl<DiscussCommentMapper, DiscussComment> implements IDiscussCommentService {

    @Autowired
    private DiscussCommentMapper discusscommentMapper;

    @Autowired
    private IOperationCountService countService;

    @Autowired
    private IRedidentService redidentService;

    @Autowired
    private IWorldService worldService;
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IDiscussService discussService;

    /**
     * 查询讨论回复表
     */
    @Override
    public List<DiscussCommentVo> selectDiscussCommentList(DiscussCommentDto dto) {
        return discusscommentMapper.selectDiscussCommentList(dto);
    }

    /**
     * 分页查询讨论回复表
     */
    @Override
    public Page<DiscussCommentVo> selectPageDiscussComment(DiscussCommentDto dto) {
        Page<DiscussCommentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return discusscommentMapper.selectPageDiscussComment(page, dto);
    }

    /**
     * 分页查询讨论回复表
     */
    @Override
    public Page<DiscussCommentVo> getPageDiscussComment(DiscussCommentDto dto) {
        Page<DiscussCommentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DiscussCommentVo> qw = new QueryWrapper<>();
        return discusscommentMapper.getPageDiscussComment(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DiscussComment add(DiscussCommentAddDto addDto) {
        DiscussComment dto=new DiscussComment();
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
        DiscussVo discussVo = discussService.getInfo(addDto.getDid());
        if (discussVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_DISCUSS_DOES_NOT_EXIST);
        }
//        redidentService.add(addDto.getWid());
//        countService.updateCount(addDto.getWid(), userid, UserOptionEnums.ADD_COMMENT);
        dto.setWid(addDto.getWid());
        dto.setWname(world.getName());
        dto.setSid(addDto.getSid());
        dto.setDid(addDto.getDid());
        dto.setUpid(0L);
        dto.setRanks(0);
        dto.setStatus(1);
        dto.setTypes(1);
        dto.setPid(0L);
        dto.setTitle(discussVo.getTitle());
        dto.setCreateName(username);
        dto.setCreateId(userid);
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setNickname(SecurityUtils.getNickName());
        dto.setComment(addDto.getComment());
        dto.setCircleUrl(SecurityUtils.getLoginUser().getAvatar());
        redidentService.add(dto.getWid());
        countService.updateCount(dto.getWid(), userid, UserOptionEnums.REPLY_COMMENT);
        discussService.updateCountReply(dto.getDid(),1);
        discusscommentMapper.insert(dto);
        return dto;
    }

    /**
     * 新增数据
     */
    @Override
    public DiscussComment reply(DiscussCommentReplyAddDto addDto) {
        DiscussComment dto=new DiscussComment();
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
        DiscussVo discussVo = discussService.getInfo(addDto.getDid());
        if (discussVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_DISCUSS_DOES_NOT_EXIST);
        }
        DiscussCommentVo commentVo = discusscommentMapper.getInfo(addDto.getUpid());
        if (commentVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_COMMIT_DOES_NOT_EXIST);
        }
        dto.setWid(addDto.getWid());
        dto.setWname(world.getName());
        dto.setSid(addDto.getSid());
        dto.setDid(addDto.getDid());
        dto.setTitle(discussVo.getTitle());
        dto.setUpid(commentVo.getId());
        if(commentVo.getPid() == 0) {
            dto.setPid(commentVo.getId());
        }else {
            dto.setPid(commentVo.getPid());
        }
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
        dto.setComment(addDto.getComment());
        dto.setCircleUrl(SecurityUtils.getLoginUser().getAvatar());
        dto.setReplyUserId(commentVo.getCreateId());
        updateCountReply(addDto.getUpid());
        updateCountReply(commentVo.getPid());

        redidentService.add(dto.getWid());
        countService.updateCount(dto.getWid(), userid, UserOptionEnums.REPLY_COMMENT);
        discusscommentMapper.insert(dto);
        return dto;
    }

    public Integer  updateCountReply(Long id){
        return discusscommentMapper.updateCountReply(id,1);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DiscussCommentDto dto) {
        return discusscommentMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return discusscommentMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public DiscussCommentVo getInfo(Long id) {
        return discusscommentMapper.getInfo(id);
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
    public int updateCountReply(Long id, Integer count) {
        return discusscommentMapper.updateCountReply(id,count);
    }
}
