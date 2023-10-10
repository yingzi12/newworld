package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.AuditContent;
import com.xinshijie.wiki.dto.AuditContentDto;
import com.xinshijie.wiki.vo.AuditContentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素内容审核 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface AuditContentMapper extends BaseMapper<AuditContent> {

    /**
     * 查询元素内容审核
     */
    List<AuditContentVo> selectAuditContentList(AuditContentDto dto);

    /**
     * 普通方法
     * 分页查询元素内容审核
     */
    Page<AuditContentVo> selectPageAuditContent(Page<AuditContentVo> page, @Param("dto") AuditContentDto dto);

    /**
     * 分页查询元素内容审核
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<AuditContentVo> getPageAuditContent(Page<AuditContentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(AuditContentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    AuditContentVo getInfo(Long id);

}
