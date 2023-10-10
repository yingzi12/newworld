package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("wiki_audit_element")
@Schema(description = "元素审核")
public class AuditElement implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private Long createId;

    private LocalDateTime createTime;

    private String createName;

    private Long updateId;

    private LocalDateTime updateTime;

    private String updateName;

    @Schema(description = "图片路径")
    private String imageUrls;

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
    private LocalDateTime auditTime;


}
