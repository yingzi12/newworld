package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.WorldSource;
import com.xinshijie.wiki.dto.WorldSourceDto;
import com.xinshijie.wiki.vo.WorldSourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 来源 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface WorldSourceMapper extends BaseMapper<WorldSource> {

    /**
     * 查询来源
     */
    List<WorldSourceVo> selectWorldSourceList(WorldSourceDto dto);

    /**
     * 普通方法
     * 分页查询来源
     */
    Page<WorldSourceVo> selectPageWorldSource(Page<WorldSourceVo> page, @Param("dto") WorldSourceDto dto);

    /**
     * 分页查询来源
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<WorldSourceVo> getPageWorldSource(Page<WorldSourceVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldSourceDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    WorldSourceVo getInfo(Long id);

    Integer deleteByWid(@Param("wid") Integer wid);

    Integer insetList(@Param("list") List<WorldSourceDto> list);
}
