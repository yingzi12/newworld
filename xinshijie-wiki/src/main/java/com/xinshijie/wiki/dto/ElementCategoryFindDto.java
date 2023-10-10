package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import lombok.Data;

@Data
public class ElementCategoryFindDto {
    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    /**
     * 元素id
     */
    @Excel(name = "元素id")
    private Long eid;

    /**
     * 分类id
     */
    @Excel(name = "分类id")
    private Long cid;

    private Long page;

    private Long size;
}
