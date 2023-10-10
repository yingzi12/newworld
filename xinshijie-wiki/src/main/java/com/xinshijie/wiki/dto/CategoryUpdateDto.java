package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryUpdateDto {
    /**
     * $column.columnComment
     */
    private Long id;
    /**
     * 名称
     */
    @Excel(name = "名称")
    private String label;
    /**
     * 世界id
     */
    @Excel(name = "世界id")
    @NotNull(message = "世界id不能为空")
    private Integer wid;
}
