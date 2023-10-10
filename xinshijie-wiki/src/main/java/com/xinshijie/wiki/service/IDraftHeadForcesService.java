package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftHeadForces;
import com.xinshijie.wiki.dto.DraftHeadForcesDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;

import java.util.List;

/**
 * <p>
 * 势力.元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IDraftHeadForcesService extends IService<DraftHeadForces> {


    /**
  * 查询信息表
  */
    List<DraftHeadForcesVo> selectDraftHeadForcesList(DraftHeadForcesDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<DraftHeadForcesVo> selectPageDraftHeadForces(DraftHeadForcesDto dto);

    /**
     * 分页查询信息表
     */
    Page<DraftHeadForcesVo> getPageDraftHeadForces(DraftHeadForcesDto dto);

    /**
     * 新增数据
     */
    DraftHeadForces add(DraftHeadForcesDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftHeadForcesDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftHeadForcesVo getInfo(Long id);

    DraftHeadForcesVo selectByWidEid(Integer wid, Long eid, Integer isDel);

    Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, Long ehid);

    Integer deleteByEidWid(Long eid, Integer wid);
}
