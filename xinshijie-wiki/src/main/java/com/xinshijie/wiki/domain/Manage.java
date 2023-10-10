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
 * 管理员
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_manage")
@Schema(description = "管理员")
public class Manage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private Long createId;

    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "贡献")
    private Integer credit;

    private String userName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Schema(description = "世界名称")
    private String wname;


    private Long userId;

    @Schema(description = "用户id")
    private String createName;

    private Integer types;

    private Long updateId;

    private String updateName;

    @Schema(description = "审核统计")
    private Integer countAuditYes;
    @Schema(description = "审核统计")
    private Integer countAuditNo;
    @Schema(description = "新增统计数")
    private Integer countAddType;
    @Schema(description = "评论数")
    private Integer countComment;
    @Schema(description = "讨论数")
    private Integer countDiscuss;
    @Schema(description = "新增元素数")
    private Integer countElement;
    @Schema(description = "编辑元素数")
    private Integer countEdit;
    @Schema(description = "观看数")
    private Integer countSee;
    @Schema(description = "收益")
    private Integer incomde;
    @Schema(description = "本月收益")
    private Integer monthlyIncomde;
}
