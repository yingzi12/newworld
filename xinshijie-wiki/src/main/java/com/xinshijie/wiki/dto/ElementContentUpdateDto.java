package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import lombok.Data;

@Data
public class ElementContentUpdateDto {
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
    /**
     * 是否允许编辑
     */
    private Integer isUpdate;

    private Integer isNew;

    private Integer isDelete;
}
