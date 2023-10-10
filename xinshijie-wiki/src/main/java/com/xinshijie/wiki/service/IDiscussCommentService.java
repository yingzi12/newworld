package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DiscussComment;
import com.xinshijie.wiki.dto.DiscussCommentAddDto;
import com.xinshijie.wiki.dto.DiscussCommentDto;
import com.xinshijie.wiki.dto.DiscussCommentReplyAddDto;
import com.xinshijie.wiki.vo.DiscussCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 讨论回复表 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface IDiscussCommentService extends IService<DiscussComment> {

    /**
     * 查询讨论回复表
     */
    List<DiscussCommentVo> selectDiscussCommentList(DiscussCommentDto dto);

    /**
     * 分页查询。普通方法
     * 查询讨论回复表
     */
    Page<DiscussCommentVo> selectPageDiscussComment(DiscussCommentDto dto);

    /**
     * 分页查询讨论回复表
     */
    Page<DiscussCommentVo> getPageDiscussComment(DiscussCommentDto dto);

    /**
     * 新增数据
     */
    DiscussComment add(DiscussCommentAddDto dto);

    DiscussComment reply(DiscussCommentReplyAddDto dto);

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

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);

    int updateCountReply(Long id, Integer count );
}
