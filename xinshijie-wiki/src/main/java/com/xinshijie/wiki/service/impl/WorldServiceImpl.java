package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.*;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Category;
import com.xinshijie.wiki.domain.Element;
import com.xinshijie.wiki.domain.Manage;
import com.xinshijie.wiki.domain.World;
import com.xinshijie.wiki.dto.WorldDto;
import com.xinshijie.wiki.dto.WorldFindDto;
import com.xinshijie.wiki.mapper.WorldMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.ManageVo;
import com.xinshijie.wiki.vo.WorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 世界 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class WorldServiceImpl extends ServiceImpl<WorldMapper, World> implements IWorldService {

    @Autowired
    private WorldMapper worldMapper;
    @Autowired
    private IManageService manageService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IElementService elementService;
    @Autowired
    private IOperationCountService countService;


    /**
     * 查询世界
     */
    @Override
    public List<WorldVo> selectWorldList(WorldFindDto dto) {
        return worldMapper.selectWorldList(dto);
    }

    /**
     * 分页查询世界
     */
    @Override
    public Page<WorldVo> selectPageWorld(WorldFindDto dto) {
        Page<WorldVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return worldMapper.selectPageWorld(page, dto);
    }

    @Override
    public List<WorldVo> findByManage(WorldFindDto dto) {
        List<WorldVo> voList = worldMapper.findByManage(dto);
        for (WorldVo vo : voList) {
            vo.setTypeName(TypeEnums.getNameByCode(vo.getTypes()));
        }
        return voList;
    }

    /**
     * 新增世界
     *
     * @param world 世界
     * @return 结果
     */
    @Override
    public World insertWorld(World world) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        Long count = manageService.countByWidUserId(null, userid);
        if (count > 20) {
            throw new ServiceException(    ResultCodeEnum.CREATE_MANAGE_UP_TO_20_WORLDS);
        }
        WorldVo vo = worldMapper.selectByName(world.getName());
        if (vo != null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_NAME_ALREADY_EXISTS_PLEASE_RE_ENTER_IT);
        }
        world.setTypesName(WorldTypeEnums.getNameByCode(world.getTypes()));
        world.setNumber(System.currentTimeMillis());
        world.setCreateId(userid);
        world.setCreateName(username);
        world.setCreateTime(LocalDateTime.now());
        int value = worldMapper.insert(world);
        Manage manage = new Manage();
        manage.setWid(world.getId());
        manage.setWname(world.getName());
        manage.setTypes(ManageTypeEnums.ADVANCED.getCode());
        manage.setUserId(userid);
        manage.setUserName(username);
        manage.setCreateId(world.getCreateId());
        manage.setCreateName(world.getCreateName());
        manageService.save(manage);
        return world;
    }

    /**
     * 修改世界
     *
     * @param world 世界
     * @return 结果
     */
    @Override
    public Integer updateWorld(World world) {
        Long userid = SecurityUtils.getUserId();
        manageService.isCheck(world.getId());
        world.setUpdateTime(DateUtils.getNowDate());
        return worldMapper.updateById(world);
    }

    /**
     * 分页查询世界
     */
    @Override
    public Page<WorldVo> getPageWorld(WorldFindDto dto) {
        Page<WorldVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<WorldVo> qw = new QueryWrapper<>();
        return worldMapper.getPageWorld(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Integer add(WorldDto dto) {
        World world = new World();
        BeanUtils.copyProperties(dto, world);
        return worldMapper.insert(world);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(WorldDto dto) {
        return worldMapper.edit(dto);
    }

//    @Override
//    public Integer updateCount(WorldDto dto){
//        return worldMapper.updateCount(dto);
//    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Integer id) {
        return worldMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public WorldVo getInfo(Integer id) {
        return worldMapper.getInfo(id);
    }

    /**
     * 删除世界信息
     *
     * @param id 世界主键
     * @return 结果
     */
    @Override
    public Integer deleteWorldById(Integer id) {
        Long userid = SecurityUtils.getUserId();
        ManageVo manage = manageService.selectByWidUserId(id, userid);
        if (manage == null || manage.getTypes() != ManageTypeEnums.ADVANCED.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }

        //判断是否存在分类
        World world = worldMapper.selectById(id);
        if (world.getStatus() != WorldStatusEnums.DRAFT.getCode()) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_HAS_BEEN_GENERATED_REPEATED);
        }
        QueryWrapper<Element> eQuery = new QueryWrapper();
        eQuery.eq("wid", id);
        Long eCount = elementService.count(eQuery);
        //判断世界状态
        if (eCount > 0) {
            throw new ServiceException(  ResultCodeEnum.ELEMENT_EXIST_AND_CANNOT_BE_DELETED);
        }
        QueryWrapper<Category> cQuery = new QueryWrapper();
        cQuery.eq("wid", id);
        Long cCount = categoryService.count(cQuery);
        //判断是否存在元素
        if (cCount > 0) {
            throw new ServiceException(ResultCodeEnum.THERE_IS_A_CATEGORY_AND_DELETION_IS_PROHIBITED);
        }
        return worldMapper.deleteById(id);
    }

    @Override
    public Integer issue(Integer wid) {
        Long userid = SecurityUtils.getUserId();
        manageService.isCheck(wid);
        QueryWrapper<Category> qwC = new QueryWrapper<>();
        qwC.eq("wid", wid);
        Long categoryCount = categoryService.count(qwC);
        if (categoryCount == 0) {
            throw new ServiceException(ResultCodeEnum.CATEGORY_DOES_NOT_EXIST);
        }
        countService.updateCount(wid, userid, UserOptionEnums.ADD_WORLD);
        World world = new World();
        world.setId(wid);
        world.setStatus(WorldStatusEnums.NORMAL.getCode());
        return worldMapper.updateById(world);
    }

    @Override
    public void updateCount(WorldDto worldDto) {
        //更新世界
        worldMapper.updateCount(worldDto);
        WorldVo vo=worldMapper.getInfo(worldDto.getId());
        Integer upgrade=RankEnums.getRankEnums(vo.getRanks()+1).getVitality();
        if(vo.getVitality() >= upgrade ){
            worldMapper.updateRank(vo.getId(),upgrade);
        }
    }

    @Override
    public Integer updateCountAuditElement(Integer wid, Integer count) {
        return worldMapper.updateCountAuditElement(wid,count);

    }

    @Override
    public Integer updateCountAuditStory(Integer wid, Integer count) {
        return worldMapper.updateCountAuditStory(wid,count);

    }

    @Override
    public Integer updateCountAuditManage(Integer wid, Integer count) {
        return worldMapper.updateCountAuditManage(wid,count);

    }

    @Override
    public Integer updateCountAuditDiscuss(Integer wid, Integer count) {
        return worldMapper.updateCountAuditDiscuss(wid,count);

    }

    @Override
    public Integer updateCountAuditComment(Integer wid, Integer count) {
        return worldMapper.updateCountAuditComment(wid,count);

    }

    public void updateElement(Integer wid, String title, Long eid) {
        WorldDto worldDto = new WorldDto();
        worldDto.setId(wid);
        worldDto.setUpdateNewElement(title);
        worldDto.setUpdateNewElementTime(LocalDateTime.now());
        worldDto.setUpdateNewElementId(eid);
        worldMapper.updateCount(worldDto);
    }

    @Override
    public WorldVo selectByName(String name) {
        return worldMapper.selectByName(name);
    }

    @Override
    public Integer like(Long id, Long userid) {
        return null;
    }

    @Override
    public Integer disagree(Long id, Long userid) {
        return null;
    }

    @Override
    public Integer see(Long id, Long userid) {
        return null;
    }

}
