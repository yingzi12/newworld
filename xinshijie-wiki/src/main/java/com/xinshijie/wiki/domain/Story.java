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
 * 故事
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_story")
@Schema(description = "故事")
public class Story implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "说明")
    private String description;

    @Schema(description = "权限 1 自由编辑,2 作者编辑")
    private Integer permission;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "类型 1原创 2转载")
    private Integer category;

    @Schema(description = "种类，1.正史  2.野史 3.异志")
    private Integer kind;

    @Schema(description = "状态 1草稿 2发布待审核  3审核成功  4审核失败 5删除  6 隐藏")
    private Integer status;

    private LocalDateTime createTime;

    private Long createId;

    private String createName;

    private String updateName;

    private LocalDateTime updateTime;

    private Long updateId;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "更新章节")
    private String updateChapter;

    @Schema(description = "更新章节id")
    private Long updateChapterId;

    @Schema(description = "更新章节名称")
    private LocalDateTime updateChapterTime;

    @Schema(description = "观看")
    private Integer countSee;

    @Schema(description = "编辑")
    private Integer countEdit;

    @Schema(description = "章节")
    private Integer countChapter;

    @Schema(description = "经验")
    private Integer exp;

    @Schema(description = "等级")
    private Integer ranks;

    private Integer countComment;

    private Integer countDiscuss;

    @Schema(description = "分类")
    private Integer types;

    @Schema(description = "审核")
    private Integer auditNumber;

    @Schema(description = "审核说明")
    private String auditContent;

    @Schema(description = "字数")
    private Integer countWorld;

    @Schema(description = "封面地址")
    private String imgUrl;

    @Schema(description = "是否收费")
    private Integer isVip;

    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "审核ID")
    private Long auditId;

    @Schema(description = "审核人")
    private String auditName;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "最后审核与发布时间")
    private LocalDateTime endTime;

    @Schema(description = "分类名称")
    private String typeName;

    @Schema(description = "传播度")
    private Integer diffuse;
    @Schema(description = "来源")
    private String source;
    @Schema(description = "是否私有")
    private Integer isPrivate;
    @Schema(description = "待审核评论数")
    private Integer countAuditDiscuss;
    @Schema(description = "待审核")
    private Integer countAuditCommit;
    @Schema(description = "待审核")
    private Integer countAuditChapter;
    @Schema(description = "待审核")
    private Integer countAuditAuthor;

    @Schema(description = "收益")
    private Integer incomde;
    @Schema(description = "本月收益")
    private Integer monthlyIncomde;
    private Integer isEdit;
}
