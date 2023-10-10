package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadThing;
import com.xinshijie.wiki.dto.ElementHeadThingDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadThingVo;
import java.util.List;

/**
 * <p>
 * 物品/材料,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementHeadThingService extends IService<ElementHeadThing> {


    /**
  * 查询信息表
  */
    List<ElementHeadThingVo> selectElementHeadThingList(ElementHeadThingDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadThingVo> selectPageElementHeadThing(ElementHeadThingDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadThingVo> getPageElementHeadThing(ElementHeadThingDto dto);

    /**
     * 新增数据
     */
    ElementHeadThing add(ElementHeadThingDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadThingDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadThingVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadThingVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);

}
