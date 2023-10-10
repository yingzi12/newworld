package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Harding;
import com.xinshijie.wiki.dto.HardingDto;
import com.xinshijie.wiki.mapper.HardingMapper;
import com.xinshijie.wiki.service.IHardingService;
import com.xinshijie.wiki.service.IOperationCountService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.HardingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class HardingServiceImpl extends ServiceImpl<HardingMapper, Harding> implements IHardingService {

    @Autowired
    private HardingMapper hardingMapper;
    @Autowired
    private IOperationCountService countService;
    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询收藏
     */
    @Override
    public List<HardingVo> selectHardingList(HardingDto dto) {
        return hardingMapper.selectHardingList(dto);
    }

    /**
     * 分页查询收藏
     */
    @Override
    public Page<HardingVo> selectPageHarding(HardingDto dto) {
        Page<HardingVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return hardingMapper.selectPageHarding(page, dto);
    }

    /**
     * 分页查询收藏
     */
    @Override
    public Page<HardingVo> getPageHarding(HardingDto dto) {
        Page<HardingVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<HardingVo> qw = new QueryWrapper<>();
        return hardingMapper.getPageHarding(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Harding add(HardingDto dto) {

        Harding value = new Harding();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        hardingMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(HardingDto dto) {
        return hardingMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return hardingMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public HardingVo getInfo(Long id) {
        return hardingMapper.getInfo(id);
    }

    @Override
    public Integer harding(Integer wid, Long sid) {
        Long userid = SecurityUtils.getUserId();

        QueryWrapper<Harding> qw = new QueryWrapper<>();
        qw.eq("sid", sid);
        qw.eq("create_id", userid);
        Long count = hardingMapper.selectCount(qw);
        if (count > 0) {
            throw new ServiceException(  ResultCodeEnum.ALREADY_FOLLOWED);
        }
        redidentService.add(wid);
//        countService.updateUserCount(userid, UserOptionEnums.ADDHARDING);

        Harding harding = new Harding();
        String username = SecurityUtils.getUsername();
        harding.setCreateName(username);
        harding.setCreateId(userid);
        harding.setSid(sid);
        return hardingMapper.insert(harding);
    }

    @Override
    public Integer hardingClose(Long sid) {
        Long userid = SecurityUtils.getUserId();
        QueryWrapper<Harding> qw = new QueryWrapper<>();
        qw.eq("sid", sid);
        qw.eq("create_id", userid);
        return hardingMapper.delete(qw);
    }

    @Override
    public HardingVo getInfoBySidUserid(Long sid, Long userid) {
        return hardingMapper.getInfoBySidUserid(sid, userid);
    }
}
