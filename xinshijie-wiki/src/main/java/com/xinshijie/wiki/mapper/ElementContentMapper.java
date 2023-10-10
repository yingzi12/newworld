package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.ElementContent;
import com.xinshijie.wiki.dto.ElementContentDto;
import com.xinshijie.wiki.dto.ElementContentFindDto;
import com.xinshijie.wiki.vo.ElementContentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素内容 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface ElementContentMapper extends BaseMapper<ElementContent> {

    /**
     * 查询元素内容
     */
    List<ElementContentVo> selectElementContentList(ElementContentFindDto dto);

    /**
     * 普通方法
     * 分页查询元素内容
     */
    Page<ElementContentVo> selectPageElementContent(Page<ElementContentVo> page, @Param("dto") ElementContentFindDto dto);

    /**
     * 分页查询元素内容
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ElementContentVo> getPageElementContent(Page<ElementContentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementContentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    Integer delByIdWidEid(@Param("id") Long id, @Param("wid") Integer wid, @Param("eid") Long eid);

    /**
     * 根据id数据
     */
    ElementContentVo getInfo(Long id);


    int insetList(@Param("list") List<ElementContentDto> list);

    List<ElementContentVo> selectByWidEid(@Param("wid") Integer wid, @Param("eid") Long eid);

    Integer updateByIdWidEid(ElementContentDto dto);

    int insertSelectEid(@Param("wid") Integer wid, @Param("deid") Long deid, @Param("eid") Long eid);

    int insertSelect(@Param("wid") Integer wid, @Param("deid") Long deid);

}
