package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import com.xinshijie.common.core.dto.BaseEntityDto;
import lombok.Data;

/**
 * 元素对象 wiki_element
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class ElementFindDto extends BaseEntityDto {
    private String intro;

    private String title;

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    private Long types;

    private String typeCode;

    /**
     * 模板类型
     */
    @Excel(name = "模板类型")
    private Long softtype;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tags;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Integer isDelete;
    private Long page;

    private Long size;
}
