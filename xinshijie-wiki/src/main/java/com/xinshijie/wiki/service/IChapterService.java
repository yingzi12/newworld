package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
 * 故事章节 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
public interface IChapterService extends IService<Chapter> {

    /**
     * 查询故事章节
     */
    List<ChapterVo> selectChapterList(ChapterFindDto dto);

    List<ChapterNameVo> selectChapterNameList(ChapterFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询故事章节
     */
    Page<ChapterVo> selectPageChapter(ChapterDto dto);

    /**
     * 分页查询故事章节
     */
    Page<ChapterVo> getPageChapter(ChapterDto dto);

    /**
     * 新增数据
     */
    Chapter add(ChapterDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ChapterDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long sid, Long scid);

    /**
     * 根据id数据
     */
    ChapterVo getInfo(Long sid, Long scid);

    ChapterIntroVo getInfoIntro(Long sid, Long scid);

    Integer like(Long id, Long userid);

    Integer disagree(Long id, Long userid);

    Integer see(Long id, Long userid);

    ChapterIntroVo previousChapter(Long scid, Integer wid, Long sid);

    ChapterIntroVo nextChapter(Long scid, Integer wid, Long sid);

    Integer updateUpidDownId(Long sid,Integer wid,Long id, Long upId,Long downId);

    ChapterVo selectSidWidSerialNumber(Long sid,Integer wid,Long serialNumber);

}
