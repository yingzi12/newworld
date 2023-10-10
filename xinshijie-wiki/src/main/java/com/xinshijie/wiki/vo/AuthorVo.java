package com.xinshijie.wiki.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 作家
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "作家")
public class AuthorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "创建人")
    private Long createId;

    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "贡献")
    private Integer credit;

    private String userName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "作家ID")
    private Long userId;

    @Schema(description = "用户id")
    private String createName;

    private Long updateId;

    private String updateName;

    @Schema(description = "管理员类别 1超级管理员 2 普通管理员")
    private Integer types;

    private Long sid;

    private String sname;

    private String sign;

    @Schema(description = "审核数")
    private Integer countAuditYes;
    @Schema(description = "审核数")
    private Integer countAuditNo;
    @Schema(description = "审核数")
    private Integer countAudit;
    private Integer countChapter;
    @Schema(description = "评论数")
    private Integer countComment;
    private Integer countDiscuss;
    @Schema(description = "编辑数")
    private Integer countEdit;

    @Schema(description = "观看数")
    private Integer countDel;

}
