package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.WorldSource;
import com.xinshijie.wiki.dto.WorldSourceDto;
import com.xinshijie.wiki.mapper.WorldSourceMapper;
import com.xinshijie.wiki.service.IWorldSourceService;
import com.xinshijie.wiki.vo.WorldSourceVo;
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
public class WorldSourceServiceImpl extends ServiceImpl<WorldSourceMapper, WorldSource> implements IWorldSourceService {

    @Autowired
    private WorldSourceMapper worldsourceMapper;

    /**
     * 查询来源
     */
    @Override
    public List<WorldSourceVo> selectWorldSourceList(WorldSourceDto dto) {
        return worldsourceMapper.selectWorldSourceList(dto);
    }

    /**
     * 分页查询来源
     */
    @Override
    public Page<WorldSourceVo> selectPageWorldSource(WorldSourceDto dto) {
        Page<WorldSourceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return worldsourceMapper.selectPageWorldSource(page, dto);
    }

    /**
     * 分页查询来源
     */
    @Override
    public Page<WorldSourceVo> getPageWorldSource(WorldSourceDto dto) {
        Page<WorldSourceVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<WorldSourceVo> qw = new QueryWrapper<>();
        return worldsourceMapper.getPageWorldSource(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public WorldSource add(WorldSourceDto dto) {
        WorldSource value = new WorldSource();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        worldsourceMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(WorldSourceDto dto) {
        return worldsourceMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return worldsourceMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public WorldSourceVo getInfo(Long id) {
        return worldsourceMapper.getInfo(id);
    }

    @Override
    public Integer insetList(List<WorldSourceDto> list) {
        return worldsourceMapper.insetList(list);
    }

    @Override
    public Integer deleteByWid(Integer wid) {
        return worldsourceMapper.deleteByWid(wid);
    }
}
