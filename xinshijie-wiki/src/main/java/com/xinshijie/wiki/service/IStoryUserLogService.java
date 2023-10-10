package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.StoryUserLog;
import com.xinshijie.wiki.dto.StoryUserLogDto;
import com.xinshijie.wiki.vo.StoryUserLogVo;
import java.util.List;

/**
 * <p>
 * 股市用户日志 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
public interface IStoryUserLogService extends IService<StoryUserLog> {


    /**
  * 查询信息表
  */
    List<StoryUserLogVo> selectStoryUserLogList(StoryUserLogDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<StoryUserLogVo> selectPageStoryUserLog(StoryUserLogDto dto);

    /**
     * 分页查询信息表
     */
    Page<StoryUserLogVo> getPageStoryUserLog(StoryUserLogDto dto);

    /**
     * 新增数据
     */
    StoryUserLog add(StoryUserLogDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(StoryUserLogDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    StoryUserLogVo getInfo(Long id);
}
