package com.xinshijie.wiki.mapper;

import com.xinshijie.wiki.dto.RecommendWorldDto;
import com.xinshijie.wiki.vo.RecommendWorldVo;
import com.xinshijie.wiki.domain.RecommendWorld;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 推荐的世界，首页使用 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */

public interface RecommendWorldMapper extends BaseMapper<RecommendWorld> {

    /**
    * 查询讨论主题表
    */
    List<RecommendWorldVo> selectListRecommendWorld(RecommendWorldDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<RecommendWorldVo> selectPageRecommendWorld(Page<RecommendWorldVo> page, @Param("dto") RecommendWorldDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<RecommendWorldVo> getPageRecommendWorld(Page<RecommendWorldVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(RecommendWorldDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    RecommendWorldVo getInfo(Long id);

    RecommendWorldVo slectWidRecType(@Param("wid") Integer wid,@Param("recType") Integer recType);

}

