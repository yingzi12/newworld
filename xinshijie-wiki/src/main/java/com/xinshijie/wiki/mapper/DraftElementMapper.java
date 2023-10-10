package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.DraftElement;
import com.xinshijie.wiki.dto.DraftElementDto;
import com.xinshijie.wiki.dto.DraftElementFindDto;
import com.xinshijie.wiki.dto.DraftElementUpdateDto;
import com.xinshijie.wiki.vo.DraftElementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素草稿 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface DraftElementMapper extends BaseMapper<DraftElement> {

    /**
     * 查询元素草稿
     */
    List<DraftElementVo> selectDraftElementList(DraftElementFindDto dto);

    /**
     * 普通方法
     * 分页查询元素草稿
     */
    Page<DraftElementVo> selectPageDraftElement(Page<DraftElementVo> page, @Param("dto") DraftElementDto dto);

    /**
     * 分页查询元素草稿
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DraftElementVo> getPageDraftElement(Page<DraftElementVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftElementDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DraftElementVo getInfo(Long id);

    /**
     * 删除元素
     *
     * @return 结果
     */
    int deleteByIdWidUserId(@Param("wid") Integer wid, @Param("id") Long deid, @Param("userId") Long userId);

    DraftElementVo selectByIdWidUserId(@Param("wid") Integer wid, @Param("id") Long deid, @Param("userId") Long userId);

    int updateStatus(DraftElementUpdateDto element);

    //自动取消发布
    Integer autorUpdateIss(@Param("nowStatus")Integer nowStatus,@Param("updateStatus")Integer updateStatus);

    //查询超时的审核
    List<DraftElementVo> findOvertimeAudit(Long size);

    List<DraftElementVo> findOvertimeIssous(Long size);

}
