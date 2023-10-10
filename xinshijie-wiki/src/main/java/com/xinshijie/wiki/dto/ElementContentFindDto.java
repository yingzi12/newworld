package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ElementContentFindDto {
    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long eid;

    /**
     * $column.columnComment
     */
    @NotNull
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String title;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Long status;

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    private Long page;

    private Long size;
}
