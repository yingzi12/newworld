package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.World;
import com.xinshijie.wiki.dto.WorldDto;
import com.xinshijie.wiki.dto.WorldFindDto;
import com.xinshijie.wiki.vo.WorldVo;

import java.util.List;

/**
 * <p>
 * 世界 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IWorldService extends IService<World> {

    /**
     * 查询世界
     */
    List<WorldVo> selectWorldList(WorldFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询世界
     */
    Page<WorldVo> selectPageWorld(WorldFindDto dto);

    List<WorldVo> findByManage(WorldFindDto dto);

    /**
     * 分页查询世界
     */
    Page<WorldVo> getPageWorld(WorldFindDto dto);

    World insertWorld(World world);

    Integer updateWorld(World world);

    /**
     * 新增数据
     */
    Integer add(WorldDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldDto dto);

    /**
     * 删除数据
     */
    Integer delById(Integer id);

    /**
     * 根据id数据
     */
    WorldVo getInfo(Integer id);

    Integer deleteWorldById(Integer id);

    Integer issue(Integer wid);

    void updateCount(WorldDto worldDto);

    Integer updateCountAuditElement(Integer wid,Integer count);

    Integer updateCountAuditStory(Integer wid,Integer count);

    Integer updateCountAuditManage(Integer wid,Integer count);

    Integer updateCountAuditDiscuss(Integer wid,Integer count);

    Integer updateCountAuditComment(Integer wid,Integer count);

    void updateElement(Integer wid, String title, Long eid);

    WorldVo selectByName(String name);

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);

    Integer see(Long id, Long userid);


}
