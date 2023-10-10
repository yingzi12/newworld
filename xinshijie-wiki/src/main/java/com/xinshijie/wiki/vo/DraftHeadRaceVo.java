package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 种族,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "DraftHeadRaceVo", description = "种族,元素头,不同的元素模板对应不同的head ")
public class DraftHeadRaceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;

    private String name;


    /**
     * 创建时间
     */
    @Schema( description = "创建时间 ")
    private String birthday;


    /**
     * 传记
     */
    @Schema( description = "传记 ")
    private String biography;


    /**
     * 背景
     */
    @Schema( description = "背景 ")
    private String background;


    /**
     * 其他
     */
    @Schema( description = "其他 ")
    private String extra;


    /**
     * 种类分类
     */
    @Schema( description = "种类分类 ")
    private String raceType;


    /**
     * 种族特征
     */
    @Schema( description = "种族特征 ")
    private String feature;


    /**
     * 主要成员
     */
    @Schema( description = "主要成员 ")
    private String mainMembers;


    /**
     * 势力范围
     */
    @Schema( description = "势力范围 ")
    private String sphereInfluence;


    /**
     * 分布范围
     */
    @Schema( description = "分布范围 ")
    private String releaseScope;


    /**
     * 主要聚集地
     */
    @Schema( description = "主要聚集地 ")
    private String mainGatheringPlace;


    /**
     * 主要势力
     */
    @Schema( description = "主要势力 ")
    private String mainForces;


    /**
     * 传说
     */
    @Schema( description = "传说 ")
    private String legend;


    /**
     * 习俗
     */
    @Schema( description = "习俗 ")
    private String custom;

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
