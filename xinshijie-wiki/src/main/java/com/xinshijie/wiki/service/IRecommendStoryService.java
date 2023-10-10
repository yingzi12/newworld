package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.RecommendStory;
import com.xinshijie.wiki.dto.RecommendStoryAddDto;
import com.xinshijie.wiki.dto.RecommendStoryDto;
import com.xinshijie.wiki.vo.RecommendStoryVo;
import java.util.List;

/**
 * <p>
 * 推荐的小说，首页使用 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */
public interface IRecommendStoryService extends IService<RecommendStory> {


    /**
  * 查询信息表
  */
    List<RecommendStoryVo> selectRecommendStoryList(RecommendStoryDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<RecommendStoryVo> selectPageRecommendStory(RecommendStoryDto dto);

    /**
     * 分页查询信息表
     */
    Page<RecommendStoryVo> getPageRecommendStory(RecommendStoryDto dto);

    /**
     * 新增数据
     */
    RecommendStory add(RecommendStoryAddDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(RecommendStoryDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    RecommendStoryVo getInfo(Long id);
}
