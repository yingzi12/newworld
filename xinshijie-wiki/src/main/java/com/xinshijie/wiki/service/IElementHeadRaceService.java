package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementHeadRace;
import com.xinshijie.wiki.dto.ElementHeadRaceDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadRaceVo;
import java.util.List;

/**
 * <p>
 * 种族,元素头,不同的元素模板对应不同的head 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementHeadRaceService extends IService<ElementHeadRace> {


    /**
  * 查询信息表
  */
    List<ElementHeadRaceVo> selectElementHeadRaceList(ElementHeadRaceDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementHeadRaceVo> selectPageElementHeadRace(ElementHeadRaceDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementHeadRaceVo> getPageElementHeadRace(ElementHeadRaceDto dto);

    /**
     * 新增数据
     */
    ElementHeadRace add(ElementHeadRaceDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementHeadRaceDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementHeadRaceVo getInfo(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    ElementHeadRaceVo selectByWidEid(Integer wid, Long eid);

    int insertSelect(Integer wid, Long deid);
}
