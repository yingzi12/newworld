package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Discuss;
import com.xinshijie.wiki.dto.DiscussDto;
import com.xinshijie.wiki.vo.DiscussVo;

import java.util.List;

/**
 * <p>
 * 讨论主题表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface IDiscussService extends IService<Discuss> {

    /**
     * 查询讨论主题表
     */
    List<DiscussVo> selectDiscussList(DiscussDto dto);

    /**
     * 分页查询。普通方法
     * 查询讨论主题表
     */
    Page<DiscussVo> selectPageDiscuss(DiscussDto dto);

    /**
     * 分页查询讨论主题表
     */
    Page<DiscussVo> getPageDiscuss(DiscussDto dto);

    /**
     * 新增数据
     */
    Discuss add(DiscussDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(DiscussDto dto);

    Integer editStatus(DiscussDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DiscussVo getInfo(Long id);

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);

    Integer updateCountReply(Long id, Integer count );
}
