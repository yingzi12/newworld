package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.StorySource;
import com.xinshijie.wiki.dto.StorySourceDto;
import com.xinshijie.wiki.vo.StorySourceVo;

import java.util.List;

/**
 * <p>
 * 来源 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
public interface IStorySourceService extends IService<StorySource> {

    /**
     * 查询来源
     */
    List<StorySourceVo> selectStorySourceList(StorySourceDto dto);

    /**
     * 分页查询。普通方法
     * 查询来源
     */
    Page<StorySourceVo> selectPageStorySource(StorySourceDto dto);

    /**
     * 分页查询来源
     */
    Page<StorySourceVo> getPageStorySource(StorySourceDto dto);

    /**
     * 新增数据
     */
    StorySource add(StorySourceDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(StorySourceDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    StorySourceVo getInfo(Long id);

    Integer insetList(List<StorySourceDto> list);

    Integer deleteBySidWid(Long sid, Integer wid);
}
