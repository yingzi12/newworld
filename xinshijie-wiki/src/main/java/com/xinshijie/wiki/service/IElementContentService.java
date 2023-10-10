package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementContent;
import com.xinshijie.wiki.dto.ElementContentDto;
import com.xinshijie.wiki.dto.ElementContentFindDto;
import com.xinshijie.wiki.vo.ElementContentVo;

import java.util.List;

/**
 * <p>
 * 元素内容 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IElementContentService extends IService<ElementContent> {

    /**
     * 查询元素内容
     */
    List<ElementContentVo> selectElementContentList(ElementContentFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素内容
     */
    Page<ElementContentVo> selectPageElementContent(ElementContentFindDto dto);

    /**
     * 分页查询元素内容
     */
    Page<ElementContentVo> getPageElementContent(ElementContentFindDto dto);

    /**
     * 新增数据
     */
    ElementContent add(ElementContentDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementContentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    Integer delByIdWidEid(Long id, Integer wid, Long eid);

    /**
     * 根据id数据
     */
    ElementContentVo getInfo(Long id);

    int insetList(List<ElementContentDto> list);

    List<ElementContentVo> selectByWidEid(Integer wid, Long eid);

    Integer updateByIdWidEid(ElementContentDto dto);

    int insertSelect(Integer wid, Long deid);

    int insertSelectEid(Integer wid, Long deid, Long eid);

}
