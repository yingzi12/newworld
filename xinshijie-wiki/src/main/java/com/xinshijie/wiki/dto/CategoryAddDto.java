package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 元素分类对象 wiki_category
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class CategoryAddDto {

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    @NotNull(message = "世界id不能为空")
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
    private Long pid;

    /**
     * 层级
     */
    @Excel(name = "层级")
    private Long tier;

    /**
     * 分类编码
     */
    @Excel(name = "分类编码")
    private String code;

    /**
     * 分类编码
     */
    @Excel(name = "分类编码")
    private String pcode;

    private String pidpath;
}
