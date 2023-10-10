package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 推荐的世界，首页使用
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */
@Data
@Schema(name = "RecommendWorldVo", description = "推荐的世界，首页使用 ")
public class RecommendWorldVo implements Serializable{

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer wid;

    private String wname;

    private String intro;


    /**
     * 推荐类别
     */
    @Schema(description = "推荐类别 ")
    private Integer recType;

    private String imgUrl;


    /**
     * 评语
     */
    @Schema(description = "评语 ")
    private String comments;

    private LocalDateTime crateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    private String createName;


    /**
     * 推荐排序
     */
    @Schema(description = "推荐排序 ")
    private Integer recSorting;


    /**
     * 世界类别，以,分割
     */
    @Schema(description = "世界类别，以,分割 ")
    private String wtype;


    /**
     * 世界标签，以,分割
     */
    @Schema(description = "世界标签，以,分割 ")
    private String wtag;


    /**
     * 是否原创 1 yes 2no
     */
    @Schema(description = "是否原创 1 yes 2no ")
    private Integer isOriginal;


}
