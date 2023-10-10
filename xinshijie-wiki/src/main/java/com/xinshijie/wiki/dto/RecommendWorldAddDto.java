package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
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
@Schema(name = "RecommendWorldDto", description = "推荐的世界，首页使用 ")
public class RecommendWorldAddDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer wid;

    /**
     * 推荐类别
     */
    @Schema( description = "推荐类别 ")
    private Integer recType;

    /**
     * 评语
     */
    @Schema( description = "评语 ")
    @Size(max = 100, message = "评语 超出最大长度 100")
    private String comments;


    /**
     * 推荐排序
     */
    @Schema( description = "推荐排序 ")
    private Integer recSorting;


}
