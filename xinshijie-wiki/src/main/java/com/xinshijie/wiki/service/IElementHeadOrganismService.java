package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadOrganism;
import com.xinshijie.wiki.dto.ElementHeadOrganismDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadOrganismVo;
import java.util.List;

/**
 * <p>
 * 生物,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementHeadOrganismService extends IService<ElementHeadOrganism> {


    /**
  * 查询信息表
  */
    List<ElementHeadOrganismVo> selectElementHeadOrganismList(ElementHeadOrganismDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadOrganismVo> selectPageElementHeadOrganism(ElementHeadOrganismDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadOrganismVo> getPageElementHeadOrganism(ElementHeadOrganismDto dto);

    /**
     * 新增数据
     */
    ElementHeadOrganism add(ElementHeadOrganismDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadOrganismDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadOrganismVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadOrganismVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);
}
