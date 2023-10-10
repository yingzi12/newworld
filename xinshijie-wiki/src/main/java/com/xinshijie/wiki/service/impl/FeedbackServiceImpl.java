package com.xinshijie.wiki.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.Feedback;
import com.xinshijie.wiki.dto.FeedbackDto;
import com.xinshijie.wiki.mapper.FeedbackMapper;
import com.xinshijie.wiki.service.IFeedbackService;
import com.xinshijie.wiki.vo.FeedbackVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 意见反馈 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;


    /**
     * 查询意见反馈
     */
    @Override
    public List<FeedbackVo> selectFeedbackList(FeedbackDto dto) {
        return feedbackMapper.selectFeedbackList(dto);
    }

    /**
     * 分页查询意见反馈
     */
    @Override
    public Page<FeedbackVo> selectPageFeedback(FeedbackDto dto) {
        Page<FeedbackVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return feedbackMapper.selectPageFeedback(page, dto);
    }

    /**
     * 分页查询意见反馈
     */
    @Override
    public Page<FeedbackVo> getPageFeedback(FeedbackDto dto) {
        Page<FeedbackVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<FeedbackVo> qw = new QueryWrapper<>();
        return feedbackMapper.getPageFeedback(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Feedback add(FeedbackDto dto) {

        Feedback value = new Feedback();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        feedbackMapper.insert(value);
        String content = "<h1>标题：" + dto.getTitle() + "_" + value.getId() + "</h1>\n" +
                "<p>内容：" + dto.getContent() + "</p>";
        MailUtil.send("xun335610@163.com", "意见反馈", content, true);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(FeedbackDto dto) {
        return feedbackMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return feedbackMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public FeedbackVo getInfo(Long id) {
        return feedbackMapper.getInfo(id);
    }
}
