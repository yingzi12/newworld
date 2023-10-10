package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.UserOptionEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Fllow;
import com.xinshijie.wiki.dto.FllowDto;
import com.xinshijie.wiki.mapper.FllowMapper;
import com.xinshijie.wiki.service.IFllowService;
import com.xinshijie.wiki.service.IOperationCountService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.FllowVo;
import com.xinshijie.wiki.vo.WorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 关注 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class FllowServiceImpl extends ServiceImpl<FllowMapper, Fllow> implements IFllowService {

    @Autowired
    private FllowMapper fllowMapper;

    @Autowired
    private IWorldService worldService;

    @Autowired
    private IOperationCountService countService;

    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询关注
     */
    @Override
    public List<FllowVo> selectFllowList(FllowDto dto) {
        Long userid = SecurityUtils.getUserId();
        dto.setCreateId(userid);
        return fllowMapper.selectFllowList(dto);
    }

    /**
     * 分页查询关注
     */
    @Override
    public Page<FllowVo> selectPageFllow(FllowDto dto) {
        Page<FllowVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return fllowMapper.selectPageFllow(page, dto);
    }

    /**
     * 分页查询关注
     */
    @Override
    public Page<FllowVo> getPageFllow(FllowDto dto) {
        Page<FllowVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<FllowVo> qw = new QueryWrapper<>();
        return fllowMapper.getPageFllow(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Fllow add(FllowDto dto) {

        FllowVo fllowVo = fllowMapper.getInfoByWidUserid(dto.getWid(), dto.getCreateId());
        if (fllowVo != null) {
            throw new ServiceException(ResultCodeEnum.REPEAT_FLLOW_ERROR);
        }
        countService.updateCount(dto.getWid(), dto.getCreateId(), UserOptionEnums.ADD_FOLLOW);


        Fllow value = new Fllow();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        value.setCreateId(SecurityUtils.getUserId());
        value.setCreateName(SecurityUtils.getUsername());
        value.setCreateTime(LocalDateTime.now());
        fllowMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(FllowDto dto) {
        return fllowMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return fllowMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public FllowVo getInfo(Long id) {
        return fllowMapper.getInfo(id);
    }

    @Override
    public FllowVo getInfoByWidUserid(Integer wid, Long userid) {
        FllowVo fllowVo = fllowMapper.getInfoByWidUserid(wid, userid);
        return fllowVo;
    }

    @Override
    public Integer fllow(Integer wid) {
        Long userid = SecurityUtils.getUserId();
        WorldVo worldVo = worldService.getInfo(wid);
        if (worldVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST_ERROR);
        }
        QueryWrapper<Fllow> qw = new QueryWrapper<>();
        qw.eq("wid", wid);
        qw.eq("create_id", userid);
        Long count = fllowMapper.selectCount(qw);
        if (count > 0) {
            throw new ServiceException(ResultCodeEnum.REPEAT_FLLOW_ERROR);
        }
        redidentService.add(wid);
        countService.updateUserCount(userid, UserOptionEnums.ADD_FOLLOW);
        Fllow fllow = new Fllow();
        String username = SecurityUtils.getUsername();
        fllow.setCreateName(username);
        fllow.setCreateId(userid);
        fllow.setWid(wid);
        fllow.setWname(worldVo.getName());
        return fllowMapper.insert(fllow);
    }

    @Override
    public Integer fllowClose(Integer wid) {
        Long userid = SecurityUtils.getUserId();

        countService.updateUserCount(userid, UserOptionEnums.DEL_FOLLOW);

        QueryWrapper<Fllow> qw = new QueryWrapper<>();
        qw.eq("wid", wid);
        qw.eq("create_id", userid);
        return fllowMapper.delete(qw);
    }
}
