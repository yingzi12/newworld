package com.xinshijie.wiki.mapper;

import com.xinshijie.wiki.dto.RecommendStoryDto;
import com.xinshijie.wiki.vo.RecommendStoryVo;
import com.xinshijie.wiki.domain.RecommendStory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 推荐的小说，首页使用 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */

public interface RecommendStoryMapper extends BaseMapper<RecommendStory> {

    /**
    * 查询讨论主题表
    */
    List<RecommendStoryVo> selectListRecommendStory(RecommendStoryDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<RecommendStoryVo> selectPageRecommendStory(Page<RecommendStoryVo> page, @Param("dto") RecommendStoryDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<RecommendStoryVo> getPageRecommendStory(Page<RecommendStoryVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(RecommendStoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    RecommendStoryVo getInfo(Long id);

    RecommendStoryVo slectSidRecType(@Param("sid") Long sid,@Param("recType") Integer recType);

}

