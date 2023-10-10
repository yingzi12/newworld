package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.DraftContent;
import com.xinshijie.wiki.dto.DraftContentDto;
import com.xinshijie.wiki.vo.DraftContentVo;

import java.util.List;

/**
 * <p>
 * 元素内容草稿 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IDraftContentService extends IService<DraftContent> {

    /**
     * 查询元素内容草稿
     */
    List<DraftContentVo> selectDraftContentList(DraftContentDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素内容草稿
     */
    Page<DraftContentVo> selectPageDraftContent(DraftContentDto dto);

    /**
     * 分页查询元素内容草稿
     */
    Page<DraftContentVo> getPageDraftContent(DraftContentDto dto);

    /**
     * 新增数据
     */
    DraftContent add(DraftContentDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftContentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftContentVo getInfo(Long id);

    Integer insetList(List<DraftContentDto> list);

    Integer updateByIdWidEid(DraftContentDto dto);

    List<DraftContentVo> selectByWidEid(Integer wid, Long eid, Integer isDel);

    Integer insetSelectContent(Long id, Integer wid, Long eid, Long userId, String userName, List<Long> ids);

    Integer deleteByEidWid(Long eid, Integer wid);
}
