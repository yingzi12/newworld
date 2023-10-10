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
 * 故事章节
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "故事章节")
public class ChapterVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "状态 1草稿 2发布 3隐藏")
    private Integer status;

    @Schema(description = "章节编号")
    private Long serialNumber;

    private LocalDateTime createTime;

    private String createName;

    private Long createId;

    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "上级")
    private Long pid;

    @Schema(description = "层级 1主标题 2小标题")
    private String level;

    @Schema(description = "章节字数")
    private Integer countWorld;

    @Schema(description = "是否收费")
    private Integer isVip;

    private Integer wid;

    private Long sid;

    @Schema(description = "内容")
    private String content;

    private Integer auditNumber;

    private String auditContent;

    private Long auditId;

    private String auditName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    private String pname;

    private String sname;

    @Schema(description = "审核统计")
    private Integer countAudit;
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
    @Schema(description = "删除元素数")
    private Integer countDel;
    @Schema(description = "新增元素数")
    private Integer countChapter;
    @Schema(description = "编辑元素数")
    private Integer countEdit;

//    private ChapterVo previous;
//
//    private ChapterVo next;

    private Long upId;

    private Long downId;

}
