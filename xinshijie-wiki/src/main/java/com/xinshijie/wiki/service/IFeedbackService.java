package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Feedback;
import com.xinshijie.wiki.dto.FeedbackDto;
import com.xinshijie.wiki.vo.FeedbackVo;

import java.util.List;

/**
 * <p>
 * 意见反馈 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface IFeedbackService extends IService<Feedback> {

    /**
     * 查询意见反馈
     */
    List<FeedbackVo> selectFeedbackList(FeedbackDto dto);

    /**
     * 分页查询。普通方法
     * 查询意见反馈
     */
    Page<FeedbackVo> selectPageFeedback(FeedbackDto dto);

    /**
     * 分页查询意见反馈
     */
    Page<FeedbackVo> getPageFeedback(FeedbackDto dto);

    /**
     * 新增数据
     */
    Feedback add(FeedbackDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(FeedbackDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    FeedbackVo getInfo(Long id);
}
