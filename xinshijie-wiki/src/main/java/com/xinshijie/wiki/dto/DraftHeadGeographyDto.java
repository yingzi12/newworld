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
@Schema(name = "DraftHeadGeographyDto", description = "地理,元素头,不同的元素模板对应不同的head ")
public class DraftHeadGeographyDto implements Serializable {

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

    private Long createId;

    @Size(max = 50, message = " 超出最大长度 50")
    private String createName;

    private LocalDateTime createTime;

    private Long updateId;

    @Size(max = 50, message = " 超出最大长度 50")
    private String updateName;

    private LocalDateTime updateTime;

    /**
     * 元素head内容id
     */
    @Schema( description = "元素head内容id ")
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
    private Long sourceEid;

    /**
     * 序号
     */
    @Schema( description = "序号 ")
    @Size(max = 100, message = "序号 超出最大长度 100")
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
    private Long souceCid;

    private Long pageNum;

    private Long pageSize;
}
