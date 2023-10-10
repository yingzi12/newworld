package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xinshijie.common.annotation.Excel;
import lombok.Data;

@Data
public class CategoryTreeVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String label;

    private String text;


    /**
     * 分类编码
     */
    @Excel(name = "分类编码")
    private String code;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;
}
