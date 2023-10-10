package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xinshijie.common.annotation.Excel;
import lombok.Data;

@Data
public class CategoryVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String label;

    /**
     * 上级id
     */
    @Excel(name = "上级id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;

    /**
     * 层级
     */
    @Excel(name = "层级")
    private Integer tier;

    /**
     * 分类编码
     */
    @Excel(name = "分类编码")
    private String code;

    private String value;

    private String pcode;

    private String pidpath;
}
