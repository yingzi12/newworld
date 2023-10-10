package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
@Schema(name = "DraftHeadGeographyVo", description = "地理,元素头,不同的元素模板对应不同的head ")
public class DraftHeadGeographyVo implements Serializable {

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
     * 气候
     */
    @Schema( description = "气候 ")
    private String climate;


    /**
     * 生活种族
     */
    @Schema( description = "生活种族 ")
    private String livingRace;


    /**
     * 所属势力
     */
    @Schema( description = "所属势力 ")
    private String affiliatedForces;


    /**
     * 主要人物
     */
    @Schema( description = "主要人物 ")
    private String mainCharacter;


    /**
     * 特产
     */
    @Schema( description = "特产 ")
    private String specialty;


    /**
     * 环境
     */
    @Schema( description = "环境 ")
    private String environment;


    /**
     * 简单地图url
     */
    @Schema( description = "简单地图url ")
    private String imgUrl;


    /**
     * 所属地区
     */
    @Schema( description = "所属地区 ")
    private String districtBelong;


    /**
     * 元素id
     */
    @Schema( description = "元素id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long districtBelongId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    private String createName;

    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    private String updateName;

    private LocalDateTime updateTime;


    /**
     * 元素head内容id
     */
    @Schema( description = "元素head内容id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceEhid;


    /**
     * 修改记录
     */
    @Schema( description = "修改记录 ")
    private String diff;


    /**
     * 元素id
     */
    @Schema( description = "元素id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceEid;


    /**
     * 序号
     */
    @Schema( description = "序号 ")
    private String serial;


    /**
     * 是否新增
     */
    @Schema( description = "是否新增 ")
    private Integer isDelete;


    /**
     * 是否删除
     */
    @Schema( description = "是否删除 ")
    private Integer isNew;


    /**
     * 是否修改
     */
    @Schema( description = "是否修改 ")
    private Integer isUpdate;


    /**
     * 元素内容id
     */
    @Schema( description = "元素内容id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long souceCid;


}
