package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ManageTypeEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Manage;
import com.xinshijie.wiki.dto.ManageDto;
import com.xinshijie.wiki.dto.ManageFindDto;
import com.xinshijie.wiki.mapper.ManageMapper;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.ManageVo;
import com.xinshijie.wiki.vo.WorldVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, Manage> implements IManageService {

    @Autowired
    private ManageMapper manageMapper;
    @Autowired
    private IWorldService worldService;

    /**
     * 查询管理员
     */
    @Override
    public List<ManageVo> selectManageList(ManageFindDto dto) {
        return manageMapper.selectManageList(dto);
    }

    /**
     * 分页查询管理员
     */
    @Override
    public Page<ManageVo> selectPageManage(ManageFindDto dto) {
        Page<ManageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return manageMapper.selectPageManage(page, dto);
    }

    /**
     * 分页查询管理员
     */
    @Override
    public Page<ManageVo> getPageManage(ManageFindDto dto) {
        Page<ManageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ManageVo> qw = new QueryWrapper<>();
        return manageMapper.getPageManage(page, qw);
    }

    @Override
    public List<ManageVo> selectManageByWid(Integer wid) {
        ManageFindDto vo = new ManageFindDto();
        vo.setWid(wid);
        return manageMapper.selectManageList(vo);
    }

    /**
     * 新增数据
     */
    @Override
    public Manage add(ManageDto dto) {
        Manage manage = new Manage();
        BeanUtils.copyProperties(dto, manage);
        manage.setRanks(0);
        manage.setCredit(0);
        manage.setIncomde(0);
        manage.setMonthlyIncomde(0);
        manage.setCountAddType(0);
        manage.setCountAuditYes(0);
        manage.setCountAuditNo(0);
        manage.setCountAddType(0);
        manage.setCountComment(0);
        manage.setCountDiscuss(0);
        manage.setCountElement(0);
        manage.setCountEdit(0);
        manage.setCountSee(0);
        manageMapper.insert(manage);
        return manage;
    }

    /**
     * 新增管理员数
     *
     * @param dto 管理员数
     * @return 结果
     */
    @Override
    public Integer insertManage(ManageDto dto) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        Long count = manageMapper.countByWidUserId(dto.getWid(), dto.getUserId());
        if (count > 0) {
            throw new ServiceException(    ResultCodeEnum.USER_ALREADY_EXISTS);
        }
        ManageVo manageVo = manageMapper.selectByWidUserId(dto.getWid(), userid);
        if (manageVo.getTypes() != ManageTypeEnums.ADVANCED.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        WorldVo world = worldService.getInfo(dto.getWid());
        Manage manage = new Manage();
        BeanUtils.copyProperties(dto, manage);
        manage.setWname(world.getName());
        manage.setCreateName(username);
        manage.setTypes(ManageTypeEnums.GENERAL.getCode());
        manage.setCreateId(userid);
        manage.setUpdateId(userid);
        manage.setUpdateName(username);
        manage.setCreateTime(LocalDateTime.now());
        manage.setUpdateTime(LocalDateTime.now());
        return manageMapper.insert(manage);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ManageDto dto) {
        return manageMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return manageMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ManageVo getInfo(Long id) {
        return manageMapper.getInfo(id);
    }

    /**
     * 删除管理员数信息
     *
     * @param id 管理员数主键
     * @return 结果
     */
    @Override
    public int deleteManageById(Long id, Integer wid) {
        Long userid = SecurityUtils.getUserId();
        ManageVo manage = manageMapper.selectByWidUserId(wid, userid);
        if (manage == null) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        if (manage.getTypes() != ManageTypeEnums.ADVANCED.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        return manageMapper.deleteManageByIdWid(id, wid);
    }

    @Override
    public ManageVo isCheck(Integer wid) {
        Long userid = SecurityUtils.getUserId();
        if (wid == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_IS_INVALID);
        }
        ManageVo manage = manageMapper.selectByWidUserId(wid, userid);
        if (manage == null) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        return manage;
    }

    @Override
    public ManageVo selectByWidUserId(Integer wid, Long uid) {
        return manageMapper.selectByWidUserId(wid, uid);
    }

    @Override
    public void updateCount(ManageDto dto) {
        //更新世界
        manageMapper.updateCount(dto);
    }

    @Override
    public Long countByWidUserId(Integer wid, Long userId) {
        return manageMapper.countByWidUserId(wid, userId);
    }


}
