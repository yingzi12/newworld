package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadRole;
import com.xinshijie.wiki.dto.ElementHeadRoleDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadRoleVo;
import java.util.List;

/**
 * <p>
 * 角色元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementHeadRoleService extends IService<ElementHeadRole> {


    /**
  * 查询信息表
  */
    List<ElementHeadRoleVo> selectElementHeadRoleList(ElementHeadRoleDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadRoleVo> selectPageElementHeadRole(ElementHeadRoleDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadRoleVo> getPageElementHeadRole(ElementHeadRoleDto dto);

    /**
     * 新增数据
     */
    ElementHeadRole add(ElementHeadRoleDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadRoleDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadRoleVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadRoleVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);
}
