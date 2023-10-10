package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.RecommendWorld;
import com.xinshijie.wiki.dto.RecommendWorldAddDto;
import com.xinshijie.wiki.dto.RecommendWorldDto;
import com.xinshijie.wiki.vo.RecommendWorldVo;
import java.util.List;

/**
 * <p>
 * 推荐的世界，首页使用 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */
public interface IRecommendWorldService extends IService<RecommendWorld> {


    /**
  * 查询信息表
  */
    List<RecommendWorldVo> selectRecommendWorldList(RecommendWorldDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<RecommendWorldVo> selectPageRecommendWorld(RecommendWorldDto dto);

    /**
     * 分页查询信息表
     */
    Page<RecommendWorldVo> getPageRecommendWorld(RecommendWorldDto dto);

    /**
     * 新增数据
     */
    RecommendWorld add(RecommendWorldAddDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(RecommendWorldDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    RecommendWorldVo getInfo(Long id);
}
