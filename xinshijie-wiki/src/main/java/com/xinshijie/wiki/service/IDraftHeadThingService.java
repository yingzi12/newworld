package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftHeadThing;
import com.xinshijie.wiki.dto.DraftHeadThingDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadThingVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;

import java.util.List;

/**
 * <p>
 * 物品/材料,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IDraftHeadThingService extends IService<DraftHeadThing> {


    /**
  * 查询信息表
  */
    List<DraftHeadThingVo> selectDraftHeadThingList(DraftHeadThingDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<DraftHeadThingVo> selectPageDraftHeadThing(DraftHeadThingDto dto);

    /**
     * 分页查询信息表
     */
    Page<DraftHeadThingVo> getPageDraftHeadThing(DraftHeadThingDto dto);

    /**
     * 新增数据
     */
    DraftHeadThing add(DraftHeadThingDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftHeadThingDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftHeadThingVo getInfo(Long id);

    DraftHeadThingVo selectByWidEid(Integer wid, Long eid, Integer isDel);

    Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, Long ehid);

    Integer deleteByEidWid(Long eid, Integer wid);
}
