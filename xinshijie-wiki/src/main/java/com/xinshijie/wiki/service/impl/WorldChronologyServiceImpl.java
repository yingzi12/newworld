package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.WorldChronology;
import com.xinshijie.wiki.dto.WorldChronologyFindDto;
import com.xinshijie.wiki.mapper.WorldChronologyMapper;
import com.xinshijie.wiki.service.IWorldChronologyService;
import com.xinshijie.wiki.dto.WorldChronologyDto;
import com.xinshijie.wiki.vo.WorldChronologyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 世界年表  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class WorldChronologyServiceImpl extends ServiceImpl<WorldChronologyMapper, WorldChronology> implements IWorldChronologyService {

    @Autowired
    private WorldChronologyMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<WorldChronologyVo> selectWorldChronologyList(WorldChronologyFindDto dto) {
        return mapper.selectListWorldChronology(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<WorldChronologyVo> selectPageWorldChronology(WorldChronologyDto dto) {
        Page<WorldChronologyVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageWorldChronology(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<WorldChronologyVo> getPageWorldChronology(WorldChronologyDto dto) {
        Page<WorldChronologyVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<WorldChronologyVo> qw = new QueryWrapper<>();
        return mapper.getPageWorldChronology(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public WorldChronology add(WorldChronologyDto dto) {
        WorldChronology value = new WorldChronology();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(WorldChronologyDto dto) {
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
    public WorldChronologyVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWid(Long id, Integer wid) {
        return mapper.delByIdWid(id,wid);
    }

    @Override
    public WorldChronologyVo selectByWid(Integer wid) {
        return mapper.selectByWid(wid);
    }

}
