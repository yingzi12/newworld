package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Element;
import com.xinshijie.wiki.dto.ElementDto;
import com.xinshijie.wiki.dto.ElementFindDto;
import com.xinshijie.wiki.vo.ElementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface ElementMapper extends BaseMapper<Element> {

    /**
     * 查询元素
     */
    List<ElementVo> selectElementList(ElementFindDto dto);

    /**
     * 普通方法
     * 分页查询元素
     */
    Page<ElementVo> selectPageElement(Page<ElementVo> page, @Param("dto") ElementFindDto dto);

    /**
     * 分页查询元素
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ElementVo> getPageElement(Page<ElementVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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

    int updateStatus(ElementDto element);

    /**
     * 删除元素
     *
     * @param id 元素主键
     * @return 结果
     */
    int deleteElementByIdWid(@Param("wid") Integer wid, @Param("eid") Long eid);

    ElementVo selectByIdWid(@Param("wid") Integer wid, @Param("eid") Long eid);

    int insertBatch(List<ElementDto> elements);

    int updateCount(ElementDto dto);

    List<ElementVo> selectElementByCidName(@Param("ids") List<Long> ids, @Param("title") String title);

    ElementVo previousElement(@Param("wid") Integer wid, @Param("eid") Long eid);

    ElementVo nextElement(@Param("wid") Integer wid, @Param("eid") Long eid);

}
