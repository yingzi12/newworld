package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Comment;
import com.xinshijie.wiki.dto.CommentDto;
import com.xinshijie.wiki.dto.CommentFindDto;
import com.xinshijie.wiki.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询评论表
     */
    List<CommentVo> selectCommentList(CommentFindDto dto);

    /**
     * 普通方法
     * 分页查询评论表
     */
    Page<CommentVo> selectPageComment(Page<CommentVo> page, @Param("dto") CommentDto dto);

    /**
     * 分页查询评论表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<CommentVo> getPageComment(Page<CommentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(CommentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    CommentVo getInfo(Long id);

    Integer updateCount(CommentDto dto);

    Integer updateCountReply(@Param("id")Long id,@Param("count")Integer count );


}
