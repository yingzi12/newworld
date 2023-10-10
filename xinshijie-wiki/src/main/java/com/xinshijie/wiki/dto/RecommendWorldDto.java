package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "RecommendWorldDto", description = "推荐的世界，首页使用 ")
public class RecommendWorldDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer wid;

    @Size(max = 100, message = " 超出最大长度 100")
    private String wname;

    @Size(max = 250, message = " 超出最大长度 250")
    private String intro;

    /**
     * 推荐类别
     */
    @Schema( description = "推荐类别 ")
    private Integer recType;

    @Size(max = 100, message = " 超出最大长度 100")
    private String imgUrl;

    /**
     * 评语
     */
    @Schema( description = "评语 ")
    @Size(max = 100, message = "评语 超出最大长度 100")
    private String comments;

    private LocalDateTime crateTime;

    private Long createBy;

    @Size(max = 100, message = " 超出最大长度 100")
    private String createName;

    /**
     * 推荐排序
     */
    @Schema( description = "推荐排序 ")
    private Integer recSorting;

    /**
     * 世界类别，以,分割
     */
    @Schema( description = "世界类别，以,分割 ")
    @Size(max = 100, message = "世界类别，以,分割 超出最大长度 100")
    private String wtype;

    /**
     * 世界标签，以,分割
     */
    @Schema( description = "世界标签，以,分割 ")
    @Size(max = 100, message = "世界标签，以,分割 超出最大长度 100")
    private String wtag;

    /**
     * 是否原创 1 yes 2no
     */
    @Schema( description = "是否原创 1 yes 2no ")
    private Integer isOriginal;

private Long pageNum;

private Long pageSize;
}
