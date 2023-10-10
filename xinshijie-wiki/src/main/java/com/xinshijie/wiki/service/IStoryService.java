package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Story;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.vo.DraftElementVo;
import com.xinshijie.wiki.vo.StoryVo;

import java.util.List;

/**
 * <p>
 * 故事 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
public interface IStoryService extends IService<Story> {

    /**
     * 查询故事
     */
    List<StoryVo> selectStoryList(StoryFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询故事
     */
    Page<StoryVo> selectPageStory(StoryDto dto);

    /**
     * 分页查询故事
     */
    Page<StoryVo> getPageStory(StoryDto dto);

    /**
     * 新增数据
     */
    Story add(StoryAddDto dto);

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

    int deleteStoryById(Long id);

    int issue(Long sid);

    int audit(AuditDto dto);

    void updateCount(StoryDto storyDto);

    void updateChapter(Long sid, String title, Long scid);

    List<StoryVo> findByAuthor(StoryFindDto dto);

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);

    Integer see(Long id, Long userid);

    Integer updateCountAuditDiscuss(Long sid,Integer count);

    Integer updateCountAuditComment(Long sid,Integer count);

    Integer updateCountAuditChapter(Long sid,Integer count);

    Integer updateCountAuditAuthor(Long sid,Integer count);

    int updateStatus(StoryDto dto);

    int autorUpdateIss();
    //查询超时的审核
    List<StoryVo> findOvertimeAudit(Long size);

    //更新入库
    Integer updateStorage(Integer wid, Long sid);
}
