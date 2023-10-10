package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.DiscussComment;
import com.xinshijie.wiki.dto.DiscussCommentDto;
import com.xinshijie.wiki.vo.DiscussCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 讨论回复表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface DiscussCommentMapper extends BaseMapper<DiscussComment> {

    /**
     * 查询讨论回复表
     */
    List<DiscussCommentVo> selectDiscussCommentList(DiscussCommentDto dto);

    /**
     * 普通方法
     * 分页查询讨论回复表
     */
    Page<DiscussCommentVo> selectPageDiscussComment(Page<DiscussCommentVo> page, @Param("dto") DiscussCommentDto dto);

    /**
     * 分页查询讨论回复表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DiscussCommentVo> getPageDiscussComment(Page<DiscussCommentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(DiscussCommentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DiscussCommentVo getInfo(Long id);

    Integer updateCount(DiscussCommentDto dto);

    Integer updateCountReply(@Param("id")Long id,@Param("count")Integer count );

}
