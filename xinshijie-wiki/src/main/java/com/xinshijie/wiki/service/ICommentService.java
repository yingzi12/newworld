package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Comment;
import com.xinshijie.wiki.dto.CommentAddDto;
import com.xinshijie.wiki.dto.CommentDto;
import com.xinshijie.wiki.dto.CommentFindDto;
import com.xinshijie.wiki.dto.CommentReplyAddDto;
import com.xinshijie.wiki.vo.CommentVo;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 查询评论表
     */
    List<CommentVo> selectCommentList(CommentFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询评论表
     */
    Page<CommentVo> selectPageComment(CommentDto dto);

    /**
     * 分页查询评论表
     */
    Page<CommentVo> getPageComment(CommentDto dto);

    /**
     * 查询世界评论列表
     *
     * @param findDto 世界评论
     * @return 世界评论集合
     */
    List<CommentVo> selectCommentByWorld(CommentFindDto findDto);

    List<CommentVo> selectCommentByStory(CommentFindDto findDto);

    /**
     * 新增数据
     */
    Comment add(CommentAddDto dto);

     Comment reply(CommentReplyAddDto addDto);
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

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);
}
