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
 * 管理员
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "管理员")
public class ManageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "贡献")
    private Integer credit;

    @Schema(description = "观看数")
    private Integer countSee;

    @Schema(description = "编辑数")
    private Integer countEdit;

    @Schema(description = "新增数")
    private Integer countNew;

    @Schema(description = "评论数")
    private Integer countComment;

    private String userName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "审核数")
    private String countAudit;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Schema(description = "用户id")
    private String createName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    private String updateName;

    private Integer types;

    private String sign;

}
