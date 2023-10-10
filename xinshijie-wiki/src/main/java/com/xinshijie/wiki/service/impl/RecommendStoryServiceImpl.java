package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.RecommendStory;
import com.xinshijie.wiki.dto.RecommendStoryAddDto;
import com.xinshijie.wiki.mapper.RecommendStoryMapper;
import com.xinshijie.wiki.service.IRecommendStoryService;
import com.xinshijie.wiki.dto.RecommendStoryDto;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.RecommendStoryVo;
import com.xinshijie.wiki.vo.StoryVo;
import com.xinshijie.wiki.vo.WorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 推荐的小说，首页使用  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class RecommendStoryServiceImpl extends ServiceImpl<RecommendStoryMapper, RecommendStory> implements IRecommendStoryService {

    @Autowired
    private RecommendStoryMapper mapper;
    @Autowired
    private IWorldService worldService;
    @Autowired
    private IStoryService storyService;

    /**
     * 查询图片信息表
     */
    @Override
    public List<RecommendStoryVo> selectRecommendStoryList(RecommendStoryDto dto) {
        return mapper.selectListRecommendStory(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<RecommendStoryVo> selectPageRecommendStory(RecommendStoryDto dto) {
        Page<RecommendStoryVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        return mapper.selectPageRecommendStory(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<RecommendStoryVo> getPageRecommendStory(RecommendStoryDto dto) {
        Page<RecommendStoryVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        QueryWrapper<RecommendStoryVo> qw = new QueryWrapper<>();
        return mapper.getPageRecommendStory(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public RecommendStory add(RecommendStoryAddDto dto) {
        RecommendStory value = new RecommendStory();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);

        StoryVo storyVo = storyService.getInfo(dto.getSid());
        value.setWname(storyVo.getName());
        value.setIntro(storyVo.getIntro());
        value.setStype(storyVo.getTypeName());
        value.setImgUrl(storyVo.getImgUrl());
        value.setWid(storyVo.getWid());
        value.setWname(storyVo.getWname());
        if ("原创".equals(storyVo.getSource())) {
            value.setIsOriginal(1);
        }else {
            value.setIsOriginal(2);
        }

        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(RecommendStoryDto dto) {
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
    public RecommendStoryVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

}
