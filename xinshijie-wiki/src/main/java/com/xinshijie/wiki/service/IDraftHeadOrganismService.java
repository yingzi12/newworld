package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftHeadOrganism;
import com.xinshijie.wiki.dto.DraftHeadOrganismDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadOrganismVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;

import java.util.List;

/**
 * <p>
 * 生物,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IDraftHeadOrganismService extends IService<DraftHeadOrganism> {


    /**
  * 查询信息表
  */
    List<DraftHeadOrganismVo> selectDraftHeadOrganismList(DraftHeadOrganismDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<DraftHeadOrganismVo> selectPageDraftHeadOrganism(DraftHeadOrganismDto dto);

    /**
     * 分页查询信息表
     */
    Page<DraftHeadOrganismVo> getPageDraftHeadOrganism(DraftHeadOrganismDto dto);

    /**
     * 新增数据
     */
    DraftHeadOrganism add(DraftHeadOrganismDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftHeadOrganismDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftHeadOrganismVo getInfo(Long id);

    DraftHeadOrganismVo selectByWidEid(Integer wid, Long eid, Integer isDel);

    Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, Long ehid);

    Integer deleteByEidWid(Long eid, Integer wid);
}
