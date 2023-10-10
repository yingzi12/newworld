package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.DicussStatusEnums;
import com.xinshijie.common.enums.UserOptionEnums;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Discuss;
import com.xinshijie.wiki.dto.DiscussDto;
import com.xinshijie.wiki.mapper.DiscussMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.DiscussVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 讨论主题表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class DiscussServiceImpl extends ServiceImpl<DiscussMapper, Discuss> implements IDiscussService {

    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private IOperationCountService countService;

    @Autowired
    private IRedidentService redidentService;
    @Autowired
    private IWorldService worldService;
    @Autowired
    private IStoryService storyService;
    /**
     * 查询讨论主题表
     */
    @Override
    public List<DiscussVo> selectDiscussList(DiscussDto dto) {
        return discussMapper.selectDiscussList(dto);
    }

    /**
     * 分页查询讨论主题表
     */
    @Override
    public Page<DiscussVo> selectPageDiscuss(DiscussDto dto) {
        Page<DiscussVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return discussMapper.selectPageDiscuss(page, dto);
    }

    /**
     * 分页查询讨论主题表
     */
    @Override
    public Page<DiscussVo> getPageDiscuss(DiscussDto dto) {
        Page<DiscussVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DiscussVo> qw = new QueryWrapper<>();
        return discussMapper.getPageDiscuss(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Discuss add(DiscussDto dto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();

        redidentService.add(dto.getWid());
        countService.updateCount(dto.getWid(), userid, UserOptionEnums.ADD_DISCUSS);

        dto.setStatus(DicussStatusEnums.WAIT.getCode());
        dto.setCreateName(username);
        dto.setCreateId(userid);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setUpdateTime(LocalDateTime.now());

        Discuss value = new Discuss();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        discussMapper.insert(value);
        if (dto.getSource() == 1 ) {
           worldService.updateCountAuditDiscuss(dto.getWid(),1);
        }else{
            storyService.updateCountAuditDiscuss(dto.getSid(),1);
        }
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(DiscussDto dto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setUpdateTime(LocalDateTime.now());
        return discussMapper.edit(dto);
    }

    @Override
    public Integer editStatus(DiscussDto dto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        dto.setUpdateId(userid);
        dto.setUpdateName(username);
        dto.setUpdateTime(LocalDateTime.now());
        if (dto.getSource() == 1 ) {
            worldService.updateCountAuditDiscuss(dto.getWid(),-1);
        }else{
            storyService.updateCountAuditDiscuss(dto.getSid(),-1);
        }
        return discussMapper.editStatus(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return discussMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public DiscussVo getInfo(Long id) {
        return discussMapper.getInfo(id);
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
    public Integer updateCountReply(Long id, Integer count) {
        return discussMapper.updateCountReply(id,count);
    }
}
