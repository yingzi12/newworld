package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.StorySource;
import com.xinshijie.wiki.dto.StorySourceDto;
import com.xinshijie.wiki.mapper.StorySourceMapper;
import com.xinshijie.wiki.service.IStorySourceService;
import com.xinshijie.wiki.vo.StorySourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 来源 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Slf4j
@Service
public class StorySourceServiceImpl extends ServiceImpl<StorySourceMapper, StorySource> implements IStorySourceService {

    @Autowired
    private StorySourceMapper storysourceMapper;

    /**
     * 查询来源
     */
    @Override
    public List<StorySourceVo> selectStorySourceList(StorySourceDto dto) {
        return storysourceMapper.selectStorySourceList(dto);
    }

    /**
     * 分页查询来源
     */
    @Override
    public Page<StorySourceVo> selectPageStorySource(StorySourceDto dto) {
        Page<StorySourceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return storysourceMapper.selectPageStorySource(page, dto);
    }

    /**
     * 分页查询来源
     */
    @Override
    public Page<StorySourceVo> getPageStorySource(StorySourceDto dto) {
        Page<StorySourceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<StorySourceVo> qw = new QueryWrapper<>();
        return storysourceMapper.getPageStorySource(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public StorySource add(StorySourceDto dto) {
        StorySource value = new StorySource();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        storysourceMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(StorySourceDto dto) {
        return storysourceMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return storysourceMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public StorySourceVo getInfo(Long id) {
        return storysourceMapper.getInfo(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public Integer insetList(List<StorySourceDto> list) {
        return storysourceMapper.insetList(list);
    }

    @Override
    public Integer deleteBySidWid(Long sid, Integer wid) {
        return storysourceMapper.deleteBySidWid(sid, wid);
    }
}
