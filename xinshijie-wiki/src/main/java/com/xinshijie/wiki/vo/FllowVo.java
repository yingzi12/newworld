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
 * 关注
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "关注")
public class FllowVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "用户id")
    private Long createId;

    @Schema(description = "用户名称")
    private String createName;

    @Schema(description = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "最新的元素")
    private String updateNewElement;

    @Schema(description = "最新的元素id")
    private Integer updateNewElementId;

    @Schema(description = "元素最新更新时间")
    private LocalDateTime updateNewElementTime;
    @Schema(description = "等级")
    private Integer ranks;
    @Schema(description = "分类")
    private Integer types;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "图片路径")
    private String imgUrl;

}
