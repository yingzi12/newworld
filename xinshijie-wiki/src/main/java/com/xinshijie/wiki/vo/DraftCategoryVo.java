package com.xinshijie.wiki.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 元素分类对应表
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素分类对应表")
public class DraftCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "元素id")
    private Long eid;

    @Schema(description = "分类id")
    private Long cid;

    private Long createId;

    private String createName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateName;

    private Long sourceEid;


    private Long page;

    private Long size;
}
