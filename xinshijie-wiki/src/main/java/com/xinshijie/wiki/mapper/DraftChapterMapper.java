package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.DraftChapter;
import com.xinshijie.wiki.dto.DraftChapterDto;
import com.xinshijie.wiki.dto.DraftChapterFindDto;
import com.xinshijie.wiki.dto.DraftElementUpdateDto;
import com.xinshijie.wiki.vo.DraftChapterVo;
import com.xinshijie.wiki.vo.DraftElementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 故事章节 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface DraftChapterMapper extends BaseMapper<DraftChapter> {

    /**
     * 查询故事章节
     */
    List<DraftChapterVo> selectDraftChapterList(@Param("dto") DraftChapterFindDto dto);

    /**
     * 普通方法
     * 分页查询故事章节
     */
    Page<DraftChapterVo> selectPageDraftChapter(Page<DraftChapterVo> page, @Param("dto") DraftChapterDto dto);

    /**
     * 分页查询故事章节
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DraftChapterVo> getPageDraftChapter(Page<DraftChapterVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(DraftChapterDto dto);

    /**
     * 删除数据
     */
    Integer delById(@Param("sid") Long sid, @Param("dscid") Long dscid);

    /**
     * 根据id数据
     */
    DraftChapterVo selectByIdSidUserId(@Param("sid") Long sid, @Param("dscid") Long dscid, @Param("userId") Long userId);
    /**
     * 根据id数据
     */
    DraftChapterVo getInfo(@Param("dscid") Long dscid);


    int updateStatus(DraftChapterDto dto);

    //自动取消发布
    Integer autorUpdateIss(@Param("nowStatus")Integer nowStatus,@Param("updateStatus")Integer updateStatus);

    //查询超时的审核
    List<DraftChapterVo> findOvertimeAudit(Long size);

    List<DraftChapterVo> findOvertimeIssous(Long size);
}
