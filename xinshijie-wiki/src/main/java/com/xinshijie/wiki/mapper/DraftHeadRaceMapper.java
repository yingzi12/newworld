package com.xinshijie.wiki.mapper;

import com.xinshijie.wiki.dto.DraftHeadRaceDto;
import com.xinshijie.wiki.vo.DraftHeadForcesVo;
import com.xinshijie.wiki.vo.DraftHeadGeographyVo;
import com.xinshijie.wiki.vo.DraftHeadRaceVo;
import com.xinshijie.wiki.domain.DraftHeadRace;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
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

public interface DraftHeadRaceMapper extends BaseMapper<DraftHeadRace> {

    /**
    * 查询讨论主题表
    */
    List<DraftHeadRaceVo> selectListDraftHeadRace(DraftHeadRaceDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<DraftHeadRaceVo> selectPageDraftHeadRace(Page<DraftHeadRaceVo> page, @Param("dto") DraftHeadRaceDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DraftHeadRaceVo> getPageDraftHeadRace(Page<DraftHeadRaceVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftHeadRaceDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftHeadRaceVo getInfo(Long id);
    DraftHeadRaceVo selectByWidEid(@Param("wid") Integer wid, @Param("eid") Long eid, @Param("isDel") Integer isDel);

    Integer deleteByEidWid(@Param("eid") Long eid, @Param("wid") Integer wid);

    Integer insetSelectContent(@Param("id") Long id, @Param("wid") Integer wid, @Param("eid") Long eid, @Param("userId") Long userId, @Param("userName") String userName, @Param("ehid")Long ehid);
}

