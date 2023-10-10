package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadForces;
import com.xinshijie.wiki.dto.ElementHeadForcesDto;
import com.xinshijie.wiki.vo.ElementContentVo;
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
public interface IElementHeadForcesService extends IService<ElementHeadForces> {


    /**
  * 查询信息表
  */
    List<ElementHeadForcesVo> selectElementHeadForcesList(ElementHeadForcesDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadForcesVo> selectPageElementHeadForces(ElementHeadForcesDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadForcesVo> getPageElementHeadForces(ElementHeadForcesDto dto);

    /**
     * 新增数据
     */
    ElementHeadForces add(ElementHeadForcesDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadForcesDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadForcesVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadForcesVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);

}
