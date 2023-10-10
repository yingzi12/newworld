package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.StoryUserLog;
import com.xinshijie.wiki.mapper.StoryUserLogMapper;
import com.xinshijie.wiki.service.IStoryUserLogService;
import com.xinshijie.wiki.dto.StoryUserLogDto;
import com.xinshijie.wiki.vo.StoryUserLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 股市用户日志  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class StoryUserLogServiceImpl extends ServiceImpl<StoryUserLogMapper, StoryUserLog> implements IStoryUserLogService {

    @Autowired
    private StoryUserLogMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<StoryUserLogVo> selectStoryUserLogList(StoryUserLogDto dto) {
        return mapper.selectListStoryUserLog(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<StoryUserLogVo> selectPageStoryUserLog(StoryUserLogDto dto) {
        Page<StoryUserLogVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        return mapper.selectPageStoryUserLog(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<StoryUserLogVo> getPageStoryUserLog(StoryUserLogDto dto) {
        Page<StoryUserLogVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        QueryWrapper<StoryUserLogVo> qw = new QueryWrapper<>();
        return mapper.getPageStoryUserLog(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public StoryUserLog add(StoryUserLogDto dto) {
        StoryUserLog value = new StoryUserLog();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(StoryUserLogDto dto) {
        return mapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return mapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public StoryUserLogVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

}
