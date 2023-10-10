package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import com.xinshijie.wiki.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 元素对象 wiki_element
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class ElementUpdateDto {
    private Long id;

    private Integer wid;

    private String wname;

    private String intro;

    private String title;

    private Long userId;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private Integer version;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tags;

    private Long sourceEid;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Integer isDelete;

    private List<Long> categoryList;

    private List<ElementContentUpdateDto> contentList;
    private List<Long> contentIdList;

    private Integer causeNumber;

    private String causeContent;

    private String extra;

    private Integer softtype;
    @Schema(description = "1电脑 2手机")
    private Integer source;
    private DraftHeadForcesDto forces;
    private DraftHeadGeographyDto geography;
    private DraftHeadOrganismDto organism;
    private DraftHeadRaceDto race;
    private DraftHeadRoleDto role;
    private DraftHeadThingDto thing;
}
