package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.AuditElement;
import com.xinshijie.wiki.dto.AuditElementDto;
import com.xinshijie.wiki.vo.AuditElementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素审核 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface AuditElementMapper extends BaseMapper<AuditElement> {

    /**
     * 查询元素审核
     */
    List<AuditElementVo> selectAuditElementList(AuditElementDto dto);

    /**
     * 普通方法
     * 分页查询元素审核
     */
    Page<AuditElementVo> selectPageAuditElement(Page<AuditElementVo> page, @Param("dto") AuditElementDto dto);

    /**
     * 分页查询元素审核
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<AuditElementVo> getPageAuditElement(Page<AuditElementVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 新增数据
     */
    Integer add(AuditElementDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(AuditElementDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    AuditElementVo getInfo(Long id);

}
