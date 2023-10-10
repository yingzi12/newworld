package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Redident;
import com.xinshijie.wiki.dto.RedidentDto;
import com.xinshijie.wiki.vo.RedidentVo;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.List;

/**
 * <p>
 * 居民数 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IRedidentService extends IService<Redident> {

    /**
     * 查询居民数
     */
    List<RedidentVo> selectRedidentList(RedidentDto dto);

    /**
     * 分页查询。普通方法
     * 查询居民数
     */
    Page<RedidentVo> selectPageRedident(RedidentDto dto);

    /**
     * 分页查询居民数
     */
    Page<RedidentVo> getPageRedident(RedidentDto dto);

    RedidentVo selectWidUserId(Integer wid,Long userId);
    /**
     * 新增数据
     */
    Integer add(Integer wid);

    /**
     * 根据id修改数据
     */
    Integer edit(int type, int wid, Long userid);

    /**
     * 根据id数据
     */
    RedidentVo getInfo(Integer id);

    void updateCount(RedidentDto dto);

}
