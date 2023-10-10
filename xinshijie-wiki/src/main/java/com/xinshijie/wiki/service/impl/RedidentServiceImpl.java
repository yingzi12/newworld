package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.RankEnums;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Redident;
import com.xinshijie.wiki.dto.RedidentDto;
import com.xinshijie.wiki.mapper.RedidentMapper;
import com.xinshijie.wiki.service.IOperationCountService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.AuthorVo;
import com.xinshijie.wiki.vo.RedidentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 居民数 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class RedidentServiceImpl extends ServiceImpl<RedidentMapper, Redident> implements IRedidentService {

    @Autowired
    private RedidentMapper redidentMapper;

    @Autowired
    private IOperationCountService countService;

    /**
     * 查询居民数
     */
    @Override
    public List<RedidentVo> selectRedidentList(RedidentDto dto) {
        return redidentMapper.selectRedidentList(dto);
    }

    /**
     * 分页查询居民数
     */
    @Override
    public Page<RedidentVo> selectPageRedident(RedidentDto dto) {
        Page<RedidentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return redidentMapper.selectPageRedident(page, dto);
    }

    /**
     * 分页查询居民数
     */
    @Override
    public Page<RedidentVo> getPageRedident(RedidentDto dto) {
        Page<RedidentVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<RedidentVo> qw = new QueryWrapper<>();
        return redidentMapper.getPageRedident(page, qw);
    }

    @Override
    public RedidentVo selectWidUserId(Integer wid, Long userId) {
        return redidentMapper.selectWidUserId(wid,userId);
    }

    /**
     * 新增数据
     */
    @Override
    public Integer add(Integer wid) {
        Long userid = SecurityUtils.getUserId();
        QueryWrapper<Redident> qw = new QueryWrapper<>();
        qw.eq("wid", wid);
        qw.eq("create_id", userid);
        String username = SecurityUtils.getUsername();
        Long count = redidentMapper.selectCount(qw);
        if (count == 0) {
            Redident redident = new Redident();
            redident.setWid(wid);
            redident.setCreateId(userid);
            redident.setUserName(username);
            redident.setCreateTime(LocalDateTime.now());
            redidentMapper.insert(redident);
        }
        return 1;
    }

    @Override
    public Integer edit(int type, int wid, Long userid) {
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return null;
    }


    /**
     * 根据id数据
     */
    @Override
    public RedidentVo getInfo(Integer id) {
        return redidentMapper.getInfo(id);
    }

    @Override
    public void updateCount(RedidentDto dto) {
        //更新世界
        redidentMapper.updateCount(dto);
        RedidentVo vo=redidentMapper.getInfoByWidCreateId(dto.getWid(),dto.getCreateId());
        Integer upgrade= RankEnums.getRankEnums(vo.getRanks()+1).getCredit();
        if(vo.getCredit() >= upgrade ){
            redidentMapper.updateRank(vo.getId(),upgrade);
        }
    }
}
