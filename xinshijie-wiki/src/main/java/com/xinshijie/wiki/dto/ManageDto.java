package com.xinshijie.wiki.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xinshijie.common.annotation.Excel;
import com.xinshijie.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

/**
 * 管理员数对象 wiki_redident
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
@TableName("wiki_manage")
public class ManageDto extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 世界id
     */
    @Excel(name = "世界id")
    private Integer wid;

    private Long createId;

    /**
     * 等级
     */
    @Excel(name = "等级")
    private Integer ranks;

    /**
     * 贡献
     */
    @Excel(name = "贡献")
    private Integer credit;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userName;


    /**
     * 世界名称
     */
    @Excel(name = "世界名称")
    private String wname;

    /**
     *
     */
    @Excel(name = "")
    private Long userId;

    private Integer types;

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
    private Integer countElement;
    @Schema(description = "编辑元素数")
    private Integer countEdit;

}
