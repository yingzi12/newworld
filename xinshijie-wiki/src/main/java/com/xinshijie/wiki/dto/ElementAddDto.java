package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import com.xinshijie.wiki.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class ElementAddDto {

    @NotNull
    private String intro;

    @NotNull
    private String title;

    /**
     * 世界id
     */
    @NotNull(message = "世界id不能为空")
    private Integer wid;

    private Long userId;

    /**
     * 世界名称
     */
    @Excel(name = "世界名称")
    private String wname;

    /**
     * 模板类型
     */
    @Excel(name = "模板类型")
//    @NotNull(message = "模板不能为空")
    private Integer softtype = 1;
    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tags;

    @Valid
    private List<ElementContentDto> contentList;

    private List<Long> categoryList;

    private String extra;

    private String pageHtml;
    @Schema(description = "1电脑 2手机")
    private Integer source;
    private DraftHeadForcesDto forces;
    private DraftHeadGeographyDto geography;
    private DraftHeadOrganismDto organism;
    private DraftHeadRaceDto race;
    private DraftHeadRoleDto role;
    private DraftHeadThingDto thing;
}
