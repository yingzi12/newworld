package com.xinshijie.wiki.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ChapterReelVo {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "状态 1草稿 2发布 3隐藏")
    private Integer status;

    @Schema(description = "章节编号")
    private Long serialNumber;

    private Integer wid;

    private Long sid;

    private String sname;

    private List<ChapterNameVo> chapterList;
}
