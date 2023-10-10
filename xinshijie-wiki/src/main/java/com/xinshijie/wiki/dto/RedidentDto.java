package com.xinshijie.wiki.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 居民数
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "居民数")
public class RedidentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private Long createId;

    @Schema(description = "等级")
    private Integer ranks;

    private String userName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "贡献")
    private Integer credit;

    @Schema(description = "评论数")
    private Integer countComment;
    @Schema(description = "讨论数")
    private Integer countDiscuss;
    @Schema(description = "新增元素数")
    private Integer countElement;
    @Schema(description = "编辑元素数")
    private Integer countEdit;
    @Schema(description = "新增元素数被拒绝")
    private Integer countElementNo;
    @Schema(description = "编辑元素数被拒绝")
    private Integer countEditNo;
    @Schema(description = "观看数")
    private Integer countSee;

    private Long page;

    private Long size;
}
