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
 * 故事章节
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_draft_chapter")
@Schema(description = "故事章节")
public class DraftChapter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private LocalDateTime updateTime;

    @Schema(description = "上级")
    private Long pid;

    @Schema(description = "层级 1主标题 2小标题")
    private Integer level;

    @Schema(description = "章节字数")
    private Integer countWorld;

    @Schema(description = "是否收费")
    private Integer isVip;

    private Integer wid;

    @Schema(description = "小说ID")
    private Long sid;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "审核类型")
    private Integer auditNumber;

    @Schema(description = "审核说明")
    private String auditContent;

    @Schema(description = "审核人")
    private Long auditId;

    @Schema(description = "审核人姓名")
    private String auditName;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "原ID")
    private Long sourceScid;

    @Schema(description = "最后审核时间与发布时间")
    private LocalDateTime endTime;
    private Integer isNew;

    private String pname;

    private String sname;

    private Long upId;

    private Long downId;
}
