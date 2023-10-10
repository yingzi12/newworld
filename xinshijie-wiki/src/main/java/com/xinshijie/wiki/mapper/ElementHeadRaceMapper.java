package com.xinshijie.wiki.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xinshijie.wiki.dto.ElementHeadRaceDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadRaceVo;
import com.xinshijie.wiki.domain.ElementHeadRace;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 种族,元素头,不同的元素模板对应不同的head Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */

public interface ElementHeadRaceMapper extends BaseMapper<ElementHeadRace> {

    /**
    * 查询讨论主题表
    */
    List<ElementHeadRaceVo> selectListElementHeadRace(ElementHeadRaceDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<ElementHeadRaceVo> selectPageElementHeadRace(Page<ElementHeadRaceVo> page, @Param("dto") ElementHeadRaceDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ElementHeadRaceVo> getPageElementHeadRace(Page<ElementHeadRaceVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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

    ElementHeadRaceVo selectByWidEid(@Param("wid") Integer wid, @Param("eid") Long eid);

    Integer delByIdWidEid(@Param("id") Long id, @Param("wid") Integer wid, @Param("eid") Long eid);

    int insertSelect(@Param("wid") Integer wid, @Param("deid") Long deid);
}

