package com.xinshijie.wiki.mapper;

import com.xinshijie.wiki.dto.StoryUserLogDto;
import com.xinshijie.wiki.vo.StoryUserLogVo;
import com.xinshijie.wiki.domain.StoryUserLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 股市用户日志 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */

public interface StoryUserLogMapper extends BaseMapper<StoryUserLog> {

    /**
    * 查询讨论主题表
    */
    List<StoryUserLogVo> selectListStoryUserLog(StoryUserLogDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<StoryUserLogVo> selectPageStoryUserLog(Page<StoryUserLogVo> page, @Param("dto") StoryUserLogDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<StoryUserLogVo> getPageStoryUserLog(Page<StoryUserLogVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(StoryUserLogDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    StoryUserLogVo getInfo(Long id);
}

