package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Story;
import com.xinshijie.wiki.dto.DraftElementUpdateDto;
import com.xinshijie.wiki.dto.StoryDto;
import com.xinshijie.wiki.dto.StoryFindDto;
import com.xinshijie.wiki.vo.DraftElementVo;
import com.xinshijie.wiki.vo.StoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 故事 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface StoryMapper extends BaseMapper<Story> {

    /**
     * 查询故事
     */
    List<StoryVo> selectStoryList(StoryFindDto dto);

    /**
     * 普通方法
     * 分页查询故事
     */
    Page<StoryVo> selectPageStory(Page<StoryVo> page, @Param("dto") StoryDto dto);

    /**
     * 分页查询故事
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<StoryVo> getPageStory(Page<StoryVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(StoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    StoryVo getInfo(Long id);

    Integer updateCount(StoryDto storyDto);
    Integer updateRank( @Param("id")Long id, @Param("upgrade")Integer upgrade);



    List<StoryVo> findByAuthor(StoryFindDto dto);

    Integer updateCountAuditDiscuss(@Param("id")Long id,@Param("count")Integer count);

    Integer updateCountAuditComment(@Param("id")Long id,@Param("count")Integer count);

    Integer updateCountAuditChapter(@Param("id")Long id,@Param("count")Integer count);

    Integer updateCountAuditAuthor(@Param("id")Long id,@Param("count")Integer count);

    int updateStatus(StoryDto dto);

    int deleteByIdWidUserId(@Param("wid") Integer wid, @Param("id") Long sid, @Param("userId") Long userId);

    //自动取消发布
    Integer autorUpdateIss(@Param("nowStatus")Integer nowStatus,@Param("updateStatus")Integer updateStatus);

    //查询超时的审核
    List<StoryVo> findOvertimeAudit(Long size);

    List<StoryVo> findOvertimeIssous(Long size);

}
