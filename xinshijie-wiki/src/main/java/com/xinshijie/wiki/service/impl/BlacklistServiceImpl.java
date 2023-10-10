package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.Blacklist;
import com.xinshijie.wiki.dto.BlacklistDto;
import com.xinshijie.wiki.mapper.BlacklistMapper;
import com.xinshijie.wiki.service.IBlacklistService;
import com.xinshijie.wiki.vo.BlacklistVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements IBlacklistService {

    @Autowired
    private BlacklistMapper blacklistMapper;

    /**
     * 查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    @Override
    public List<BlacklistVo> selectBlacklistList(BlacklistDto dto) {
        return blacklistMapper.selectBlacklistList(dto);
    }

    /**
     * 分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    @Override
    public Page<BlacklistVo> selectPageBlacklist(BlacklistDto dto) {
        Page<BlacklistVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return blacklistMapper.selectPageBlacklist(page, dto);
    }

    /**
     * 分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    @Override
    public Page<BlacklistVo> getPageBlacklist(BlacklistDto dto) {
        Page<BlacklistVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<BlacklistVo> qw = new QueryWrapper<>();
        return blacklistMapper.getPageBlacklist(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Integer add(BlacklistDto dto) {
        Blacklist value = new Blacklist();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        return blacklistMapper.insert(value);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(BlacklistDto dto) {
        return blacklistMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return blacklistMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public BlacklistVo getInfo(Long id) {
        return blacklistMapper.getInfo(id);
    }
}
