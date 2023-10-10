package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xinshijie.common.annotation.Excel;
import lombok.Data;

@Data
public class CategorySimplyVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
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
}
