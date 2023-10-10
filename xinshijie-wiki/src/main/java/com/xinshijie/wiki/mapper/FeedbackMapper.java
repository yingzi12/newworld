package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Feedback;
import com.xinshijie.wiki.dto.FeedbackDto;
import com.xinshijie.wiki.vo.FeedbackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 意见反馈 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 查询意见反馈
     */
    List<FeedbackVo> selectFeedbackList(FeedbackDto dto);

    /**
     * 普通方法
     * 分页查询意见反馈
     */
    Page<FeedbackVo> selectPageFeedback(Page<FeedbackVo> page, @Param("dto") FeedbackDto dto);

    /**
     * 分页查询意见反馈
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<FeedbackVo> getPageFeedback(Page<FeedbackVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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
