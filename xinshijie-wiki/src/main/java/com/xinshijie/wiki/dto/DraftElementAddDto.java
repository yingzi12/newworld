package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class DraftElementAddDto {

    @NotNull
    private String intro;

    @NotNull
    private String title;

    /**
     * 世界id
     */
    @NotNull(message = "世界id不能为空")
    private Integer wid;

    /**
     * 世界名称
     */
    @Excel(name = "世界名称")
    private String wname;

    /**
     * 模板类型
     */
    @Excel(name = "模板类型")
    @NotNull(message = "模板不能为空")
    private Integer softtype;
    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tags;

    @Valid
    private List<DraftContentDto> contentList;

    private List<Long> categoryList;

    private String extra;

    @Schema(description = "最后审核与发布时间")
    private LocalDateTime endTime;

    private String pageHtml;
    @Schema(description = "1电脑 2手机")
    private Integer source;
}
