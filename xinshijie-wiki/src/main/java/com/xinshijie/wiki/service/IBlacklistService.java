package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Blacklist;
import com.xinshijie.wiki.dto.BlacklistDto;
import com.xinshijie.wiki.vo.BlacklistVo;

import java.util.List;

/**
 * <p>
 * 世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IBlacklistService extends IService<Blacklist> {

    /**
     * 查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    List<BlacklistVo> selectBlacklistList(BlacklistDto dto);

    /**
     * 分页查询。普通方法
     * 查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    Page<BlacklistVo> selectPageBlacklist(BlacklistDto dto);

    /**
     * 分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    Page<BlacklistVo> getPageBlacklist(BlacklistDto dto);

    /**
     * 新增数据
     */
    Integer add(BlacklistDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(BlacklistDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    BlacklistVo getInfo(Long id);
}
