package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftHeadRole;
import com.xinshijie.wiki.dto.DraftHeadRoleDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadRoleVo;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;

import java.util.List;

/**
 * <p>
 * 角色元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IDraftHeadRoleService extends IService<DraftHeadRole> {


    /**
  * 查询信息表
  */
    List<DraftHeadRoleVo> selectDraftHeadRoleList(DraftHeadRoleDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<DraftHeadRoleVo> selectPageDraftHeadRole(DraftHeadRoleDto dto);

    /**
     * 分页查询信息表
     */
    Page<DraftHeadRoleVo> getPageDraftHeadRole(DraftHeadRoleDto dto);

    /**
     * 新增数据
     */
    DraftHeadRole add(DraftHeadRoleDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftHeadRoleDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftHeadRoleVo getInfo(Long id);

    DraftHeadRoleVo selectByWidEid(Integer wid, Long eid, Integer isDel);

    Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, Long ehid);

    Integer deleteByEidWid(Long eid, Integer wid);
}
