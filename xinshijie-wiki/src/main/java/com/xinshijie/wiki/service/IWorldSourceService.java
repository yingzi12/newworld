package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.WorldSource;
import com.xinshijie.wiki.dto.WorldSourceDto;
import com.xinshijie.wiki.vo.WorldSourceVo;

import java.util.List;

/**
 * <p>
 * 来源 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
public interface IWorldSourceService extends IService<WorldSource> {

    /**
     * 查询来源
     */
    List<WorldSourceVo> selectWorldSourceList(WorldSourceDto dto);

    /**
     * 分页查询。普通方法
     * 查询来源
     */
    Page<WorldSourceVo> selectPageWorldSource(WorldSourceDto dto);

    /**
     * 分页查询来源
     */
    Page<WorldSourceVo> getPageWorldSource(WorldSourceDto dto);

    /**
     * 新增数据
     */
    WorldSource add(WorldSourceDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldSourceDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    WorldSourceVo getInfo(Long id);

    Integer insetList(List<WorldSourceDto> list);

    Integer deleteByWid(Integer wid);
}
