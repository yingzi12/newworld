package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.DraftContent;
import com.xinshijie.wiki.dto.DraftContentDto;
import com.xinshijie.wiki.vo.DraftContentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素内容草稿 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface DraftContentMapper extends BaseMapper<DraftContent> {

    /**
     * 查询元素内容草稿
     */
    List<DraftContentVo> selectDraftContentList(DraftContentDto dto);

    /**
     * 普通方法
     * 分页查询元素内容草稿
     */
    Page<DraftContentVo> selectPageDraftContent(Page<DraftContentVo> page, @Param("dto") DraftContentDto dto);

    /**
     * 分页查询元素内容草稿
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DraftContentVo> getPageDraftContent(Page<DraftContentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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

    List<DraftContentVo> selectByWidEid(@Param("wid") Integer wid, @Param("eid") Long eid, @Param("isDel") Integer isDel);

    Integer updateByIdWidEid(DraftContentDto dto);

    Integer insetSelectContent(@Param("id") Long id, @Param("wid") Integer wid, @Param("eid") Long eid, @Param("userId") Long userId, @Param("userName") String userName, @Param("ids") List<Long> ids);

    Integer deleteByEidWid(@Param("eid") Long eid, @Param("wid") Integer wid);
}
