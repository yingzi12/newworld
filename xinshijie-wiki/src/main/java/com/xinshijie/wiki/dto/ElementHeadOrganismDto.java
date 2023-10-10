package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "ElementHeadOrganismDto", description = "生物,元素头,不同的元素模板对应不同的head ")
public class ElementHeadOrganismDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer wid;

    private Long weid;

    @Size(max = 200, message = " 超出最大长度 200")
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
    @Size(max = 100, message = "种类 超出最大长度 100")
    private String kind;

    /**
     * 特征
     */
    @Schema( description = "特征 ")
    @Size(max = 100, message = "特征 超出最大长度 100")
    private String feature;

    /**
     * 生长/生活习惯
     */
    @Schema( description = "生长/生活习惯 ")
    @Size(max = 100, message = "生长/生活习惯 超出最大长度 100")
    private String growthHabits;

    /**
     * 分布范围
     */
    @Schema( description = "分布范围 ")
    @Size(max = 100, message = "分布范围 超出最大长度 100")
    private String distributionArea;

    /**
     * 特殊说明
     */
    @Schema( description = "特殊说明 ")
    @Size(max = 100, message = "特殊说明 超出最大长度 100")
    private String specialInstructions;

    private Long page;

    private Long size;
}
