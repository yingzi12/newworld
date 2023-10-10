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
 * 元素内容
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素内容")
public class ElementContentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long eid;

    private String title;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "世界id")
    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    private String createName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "序号")
    private Integer serial;

    private Long page;

    private Long size;

    @Schema(description = "扩展")
    private String extra;

    /**
     * 是否允许编辑
     */
    private Integer isUpdate = 0;

    private Integer isNew = 0;
}
