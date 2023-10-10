package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadGeography;
import com.xinshijie.wiki.dto.ElementHeadGeographyDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadGeographyVo;
import java.util.List;

/**
 * <p>
 * 地理,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementHeadGeographyService extends IService<ElementHeadGeography> {


    /**
  * 查询信息表
  */
    List<ElementHeadGeographyVo> selectElementHeadGeographyList(ElementHeadGeographyDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadGeographyVo> selectPageElementHeadGeography(ElementHeadGeographyDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadGeographyVo> getPageElementHeadGeography(ElementHeadGeographyDto dto);

    /**
     * 新增数据
     */
    ElementHeadGeography add(ElementHeadGeographyDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadGeographyDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadGeographyVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadGeographyVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);
}
