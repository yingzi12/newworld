package com.xinshijie.wiki.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xinshijie.wiki.dto.WorldChronologyDto;
import com.xinshijie.wiki.dto.WorldChronologyFindDto;
import com.xinshijie.wiki.vo.ElementImgVo;
import com.xinshijie.wiki.vo.WorldChronologyVo;
import com.xinshijie.wiki.domain.WorldChronology;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 世界年表 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */

public interface WorldChronologyMapper extends BaseMapper<WorldChronology> {

    /**
    * 查询讨论主题表
    */
    List<WorldChronologyVo> selectListWorldChronology(WorldChronologyFindDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<WorldChronologyVo> selectPageWorldChronology(Page<WorldChronologyVo> page, @Param("dto") WorldChronologyDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<WorldChronologyVo> getPageWorldChronology(Page<WorldChronologyVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldChronologyDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    WorldChronologyVo getInfo(Long id);

    Integer delByIdWid(@Param("id")Long id,@Param("wid")Integer wid);


    WorldChronologyVo selectByWid(@Param("wid")Integer wid);
}

