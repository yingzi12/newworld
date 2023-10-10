package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DraftContentUpdateDto {
    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long eid;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String title;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    @Schema(description = "扩展")
    private String extra;

    /**
     * 是否允许编辑
     */
    private Integer isUpdate = 0;

    private Integer isNew = 0;
}
