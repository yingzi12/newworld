package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 地理,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "ElementHeadGeographyDto", description = "地理,元素头,不同的元素模板对应不同的head ")
public class ElementHeadGeographyDto implements Serializable {

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
     * 气候
     */
    @Schema( description = "气候 ")
    @Size(max = 100, message = "气候 超出最大长度 100")
    private String climate;

    /**
     * 生活种族
     */
    @Schema( description = "生活种族 ")
    @Size(max = 100, message = "生活种族 超出最大长度 100")
    private String livingRace;

    /**
     * 所属势力
     */
    @Schema( description = "所属势力 ")
    @Size(max = 100, message = "所属势力 超出最大长度 100")
    private String affiliatedForces;

    /**
     * 主要人物
     */
    @Schema( description = "主要人物 ")
    @Size(max = 100, message = "主要人物 超出最大长度 100")
    private String mainCharacter;

    /**
     * 特产
     */
    @Schema( description = "特产 ")
    @Size(max = 100, message = "特产 超出最大长度 100")
    private String specialty;

    /**
     * 环境
     */
    @Schema( description = "环境 ")
    @Size(max = 100, message = "环境 超出最大长度 100")
    private String environment;

    /**
     * 简单地图url
     */
    @Schema( description = "简单地图url ")
    @Size(max = 100, message = "简单地图url 超出最大长度 100")
    private String imgUrl;

    /**
     * 所属地区
     */
    @Schema( description = "所属地区 ")
    @Size(max = 100, message = "所属地区 超出最大长度 100")
    private String districtBelong;

    /**
     * 元素id
     */
    @Schema( description = "元素id ")
    private Long districtBelongId;

    private Long page;

    private Long size;
}
