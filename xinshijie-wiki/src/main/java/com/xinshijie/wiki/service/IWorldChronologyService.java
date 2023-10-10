package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.WorldChronology;
import com.xinshijie.wiki.dto.WorldChronologyDto;
import com.xinshijie.wiki.dto.WorldChronologyFindDto;
import com.xinshijie.wiki.vo.ElementHeadGeographyVo;
import com.xinshijie.wiki.vo.WorldChronologyVo;
import java.util.List;

/**
 * <p>
 * 世界年表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IWorldChronologyService extends IService<WorldChronology> {


    /**
  * 查询信息表
  */
    List<WorldChronologyVo> selectWorldChronologyList(WorldChronologyFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<WorldChronologyVo> selectPageWorldChronology(WorldChronologyDto dto);

    /**
     * 分页查询信息表
     */
    Page<WorldChronologyVo> getPageWorldChronology(WorldChronologyDto dto);

    /**
     * 新增数据
     */
    WorldChronology add(WorldChronologyDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldChronologyDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    WorldChronologyVo getInfo(Long id);

    Integer delByIdWid(Long id, Integer wid);

    WorldChronologyVo selectByWid(Integer wid);
}
