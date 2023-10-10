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
 * 元素审核
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素审核")
public class AuditElementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "模板类型")
    private Integer softtype;

    @Schema(description = "查看")
    private Integer countSee;

    @Schema(description = "编辑")
    private Integer countEdit;

    @Schema(description = "评论")
    private Integer countComment;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateName;

    @Schema(description = "图片路径")
    private String imageUrls;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceEid;

    @Schema(description = "审核内容")
    private String audit;

    @Schema(description = "是否新增")
    private Integer isNew;

    @Schema(description = "审核人id")
    private Integer auditId;

    @Schema(description = "审核姓名")
    private String auditName;

    @Schema(description = "审核时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    private Long page;

    private Long size;
}
