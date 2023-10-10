package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Element;
import com.xinshijie.wiki.dto.ElementAddDto;
import com.xinshijie.wiki.dto.ElementDto;
import com.xinshijie.wiki.dto.ElementFindDto;
import com.xinshijie.wiki.dto.ElementUpdateDto;
import com.xinshijie.wiki.vo.ElementVo;
import com.xinshijie.wiki.vo.SimpleElementVo;

import java.util.List;

/**
 * <p>
 * 元素 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IElementService extends IService<Element> {

    /**
     * 查询元素
     */
    List<ElementVo> selectElementList(ElementFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询元素
     */
    Page<ElementVo> selectPageElement(ElementFindDto dto);

    /**
     * 分页查询元素
     */
    Page<ElementVo> getPageElement(ElementFindDto dto);

    /**
     * 新增数据
     */
    Element add(ElementDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementVo getInfo(Long id);

    SimpleElementVo insertElement(ElementAddDto addDto);

    SimpleElementVo updateElement(ElementUpdateDto dto);


    int updateStatus(ElementUpdateDto element);

    int deleteElementByIdWid(Integer wid, Long eid);

    void isCheck(Long id, Long userId);

    ElementVo selectElement(Integer wid, Long eid);

    ElementVo getSingle(Integer wid, Long eid);

    int insertBatch(List<ElementDto> elements);

    Integer see(Long id, Long userid);

    Integer edit(Long id, Long userid);

    List<ElementVo> selectElementByCidName(List<Long> ids, String title);

    Integer unlock(Integer wid, Long eid);

    Integer lock(Integer wid, Long eid);

    ElementVo previousElement(Integer wid, Long eid);

    ElementVo nextElement(Integer wid, Long eid);
}
