package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 势力.元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "ElementHeadForcesVo", description = "势力.元素头,不同的元素模板对应不同的head ")
public class ElementHeadForcesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;

    private String name;


    /**
     * 势力创建时间
     */
    @Schema( description = "势力创建时间 ")
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

    private String extra;


    /**
     * 势力类型
     */
    @Schema( description = "势力类型 ")
    private String farcesType;


    /**
     * 势力特征
     */
    @Schema( description = "势力特征 ")
    private String feature;


    /**
     * 势力创建者
     */
    @Schema( description = "势力创建者 ")
    private String forceCreator;


    /**
     * 主要成员
     */
    @Schema( description = "主要成员 ")
    private String mainMembers;


    /**
     * 势力架构
     */
    @Schema( description = "势力架构 ")
    private String architecture;


    /**
     * 势力范围
     */
    @Schema( description = "势力范围 ")
    private String sphereInfluence;


}
