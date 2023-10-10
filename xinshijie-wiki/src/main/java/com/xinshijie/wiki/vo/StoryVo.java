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
 * 故事
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "故事")
public class StoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @Schema(description = "状态 1草稿 2发布待审核  3审核成功  4审核失败 5删除  6 隐藏")
    private Integer status;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long createId;

    private String createName;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long updateId;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "更新章节")
    private String updateChapter;

    @Schema(description = "更新章节id")
    private Long updateChapterId;

    @Schema(description = "更新章节名称")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateChapterTime;

    @Schema(description = "传播度")
    private Integer diffuse;

    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "分类")
    private Integer types;

    private String typeName;

    @Schema(description = "审核")
    private Integer auditNumber;

    @Schema(description = "审核说明")
    private String auditContent;

    @Schema(description = "字数")
    private Integer countWord;

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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @Schema(description = "最后审核与发布时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private String source;

    @Schema(description = "管理员")
    private Integer countAuthor;
    @Schema(description = "评论数")
    private Integer countComment;
    private Integer countDiscuss;
    @Schema(description = "元素数")
    private Integer countChapter;

    private Integer countEdit;
    private Integer countHarding;
    @Schema(description = "点赞")
    private Integer countLike;
    @Schema(description = "查看")
    private Integer countSee;

    private Integer countAudit;
    @Schema(description = "是否私有")
    private Integer isPrivate;
    @Schema(description = "待审核")
    private Integer countAuditDiscuss;
    @Schema(description = "待审核")
    private Integer countAuditCommit;
    @Schema(description = "待审核")
    private Integer countAuditChapter;
    @Schema(description = "待审核")
    private Integer countAuditAuthor;
    private Integer authorType;

}
