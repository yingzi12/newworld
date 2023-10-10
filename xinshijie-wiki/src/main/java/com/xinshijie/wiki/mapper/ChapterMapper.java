package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Chapter;
import com.xinshijie.wiki.dto.ChapterDto;
import com.xinshijie.wiki.dto.ChapterFindDto;
import com.xinshijie.wiki.vo.ChapterIntroVo;
import com.xinshijie.wiki.vo.ChapterNameVo;
import com.xinshijie.wiki.vo.ChapterVo;
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
public interface ChapterMapper extends BaseMapper<Chapter> {

    /**
     * 查询故事章节
     */
    List<ChapterVo> selectChapterList(ChapterFindDto dto);

    List<ChapterNameVo> selectChapterNameList(ChapterFindDto dto);

    /**
     * 普通方法
     * 分页查询故事章节
     */
    Page<ChapterVo> selectPageChapter(Page<ChapterVo> page, @Param("dto") ChapterDto dto);

    /**
     * 分页查询故事章节
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ChapterVo> getPageChapter(Page<ChapterVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(ChapterDto dto);

    /**
     * 删除数据
     */
    Integer delById(@Param("sid") Long sid, @Param("scid") Long scid);

    /**
     * 根据id数据
     */
    ChapterVo getInfo(@Param("sid") Long sid, @Param("scid") Long scid);
    ChapterIntroVo getInfoIntro(@Param("sid") Long sid, @Param("scid") Long scid);

    Integer updateCount(ChapterDto chapterDto);

    ChapterIntroVo previousChapter(@Param("upId") Long upId, @Param("wid") Integer wid, @Param("sid") Long sid);

    ChapterIntroVo nextChapter(@Param("downId") Long downId, @Param("wid") Integer wid, @Param("sid") Long sid);

    Integer updateUpidDownId(@Param("sid") Long sid, @Param("wid") Integer wid,@Param("id") Long id,@Param("upId") Long upId,@Param("downId") Long downId);

    ChapterVo selectSidWidSerialNumber(@Param("sid") Long sid, @Param("wid") Integer wid,@Param("serialNumber") Long serialNumber);

}
