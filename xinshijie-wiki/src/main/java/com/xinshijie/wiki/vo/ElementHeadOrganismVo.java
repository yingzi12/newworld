package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 生物,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "ElementHeadOrganismVo", description = "生物,元素头,不同的元素模板对应不同的head ")
public class ElementHeadOrganismVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;

    private String name;


    /**
     * 传记
     */
    @Schema( description = "传记 ")
    private String biography;

    private String extra;


    /**
     * 种类
     */
    @Schema( description = "种类 ")
    private String kind;


    /**
     * 特征
     */
    @Schema( description = "特征 ")
    private String feature;


    /**
     * 生长/生活习惯
     */
    @Schema( description = "生长/生活习惯 ")
    private String growthHabits;


    /**
     * 分布范围
     */
    @Schema( description = "分布范围 ")
    private String distributionArea;


    /**
     * 特殊说明
     */
    @Schema( description = "特殊说明 ")
    private String specialInstructions;


}
