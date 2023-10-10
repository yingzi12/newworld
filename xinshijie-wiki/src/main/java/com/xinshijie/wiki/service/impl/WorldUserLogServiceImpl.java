package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.WorldUserLog;
import com.xinshijie.wiki.mapper.WorldUserLogMapper;
import com.xinshijie.wiki.service.IWorldUserLogService;
import com.xinshijie.wiki.dto.WorldUserLogDto;
import com.xinshijie.wiki.vo.WorldUserLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 世界用户日志  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class WorldUserLogServiceImpl extends ServiceImpl<WorldUserLogMapper, WorldUserLog> implements IWorldUserLogService {

    @Autowired
    private WorldUserLogMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<WorldUserLogVo> selectWorldUserLogList(WorldUserLogDto dto) {
        return mapper.selectListWorldUserLog(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<WorldUserLogVo> selectPageWorldUserLog(WorldUserLogDto dto) {
        Page<WorldUserLogVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        return mapper.selectPageWorldUserLog(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<WorldUserLogVo> getPageWorldUserLog(WorldUserLogDto dto) {
        Page<WorldUserLogVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        QueryWrapper<WorldUserLogVo> qw = new QueryWrapper<>();
        return mapper.getPageWorldUserLog(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public WorldUserLog add(WorldUserLogDto dto) {
        WorldUserLog value = new WorldUserLog();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(WorldUserLogDto dto) {
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
    public WorldUserLogVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

}
