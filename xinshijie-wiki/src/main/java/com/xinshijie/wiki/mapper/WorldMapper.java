package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.World;
import com.xinshijie.wiki.dto.WorldDto;
import com.xinshijie.wiki.dto.WorldFindDto;
import com.xinshijie.wiki.vo.WorldVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 世界 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface WorldMapper extends BaseMapper<World> {

    /**
     * 查询世界
     */
    List<WorldVo> selectWorldList(WorldFindDto dto);

    /**
     * 普通方法
     * 分页查询世界
     */
    Page<WorldVo> selectPageWorld(Page<WorldVo> page, @Param("dto") WorldFindDto dto);

    /**
     * 分页查询世界
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<WorldVo> getPageWorld(Page<WorldVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldDto dto);

    Integer updateCount(WorldDto dto);
    Integer updateRank( @Param("id")Integer id, @Param("upgrade")Integer upgrade);

    Integer updateCountAuditElement( @Param("wid")Integer wid, @Param("count")Integer count);

    Integer updateCountAuditStory( @Param("wid")Integer wid, @Param("count")Integer count);

    Integer updateCountAuditManage( @Param("wid")Integer wid, @Param("count")Integer count);

    Integer updateCountAuditDiscuss( @Param("wid")Integer wid, @Param("count")Integer count);

    Integer updateCountAuditComment( @Param("wid")Integer wid, @Param("count")Integer count);
    /**
     * 删除数据
     */
    Integer delById(Integer id);

    /**
     * 根据id数据
     */
    WorldVo getInfo(Integer id);

    List<WorldVo> findByManage(WorldFindDto dto);

    WorldVo selectByName(@Param("name") String name);
}
