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
 * 世界
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_world")
@Schema(description = "世界")
public class World implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "说明")
    private String description;

    @Schema(description = "分类")
    private Integer types;

    private String typesName;

    @Schema(description = "简介")
    private String intro;

    private String tags;


    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "经验值")
    private Integer exp;

    @Schema(description = "最新的元素")
    private String updateNewElement;

    @Schema(description = "最新的元素id")
    private Integer updateNewElementId;

    @Schema(description = "得分")
    private Integer scores;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建人id")
    private Long createId;

    @Schema(description = "更新人名称")
    private String updateName;

    @Schema(description = "更新人id")
    private Long updateId;

    @Schema(description = "发布之后不能修改名称")
    private Integer status;

    @Schema(description = "图片路径")
    private String imgUrl;

    @Schema(description = "元素最新更新时间")
    private LocalDateTime updateNewElementTime;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "是否私有")
    private Integer isPrivate;

    private Long number;

    @Schema(description = "管理员")
    private Integer countAdmin;
    @Schema(description = "分类数")
    private Integer countAddType;
    @Schema(description = "评论数")
    private Integer countComment;
    private Integer countDiscuss;
    @Schema(description = "元素数")
    private Integer countElement;
    private Integer countEdit;
    private Integer countFllow;
    @Schema(description = "点赞")
    private Integer countLike;
    @Schema(description = "居民数")
    private Integer countResident;
    @Schema(description = "查看")
    private Integer countSee;
    private Integer countStory;
    @Schema(description = "待审核")
    private Integer countAuditStory;
    @Schema(description = "待审核")
    private Integer countAuditElement;
    @Schema(description = "待审核")
    private Integer countAuditDiscuss;
    @Schema(description = "待审核")
    private Integer countAuditCommit;
    @Schema(description = "待审核")
    private Integer countAuditManage;
    @Schema(description = "收益")
    private Integer incomde;
    @Schema(description = "本月收益")
    private Integer monthlyIncomde;
    private Integer isEdit;
}
