package com.xinshijie.wiki.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 讨论回复表
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "讨论回复表")
public class DiscussCommentAddDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "世界id")
    private Integer wid;

    private Long sid;

    @Schema(description = "评论内容")
    private String comment;

    @Schema(description = "元素id")
    private Long eid;

    @Schema(description = "讨论id")
    private Long did;


    @Schema(description = "数据源 1,世界  2故事", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer source;
}
