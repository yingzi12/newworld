package com.xinshijie.wiki.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xinshijie.common.annotation.Excel;
import com.xinshijie.common.core.domain.BaseEntity;
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
public class AuthorFindDto extends BaseEntity {
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

    private Long sid;
    /**
     * 等级
     */
    @Excel(name = "等级")
    private Long ranks;

    /**
     * 贡献
     */
    @Excel(name = "贡献")
    private Long credit;

    /**
     * 观看数
     */
    @Excel(name = "观看数")
    private Long countSee;

    /**
     * 编辑数
     */
    @Excel(name = "编辑数")
    private Long countEdit;

    /**
     * 新增数
     */
    @Excel(name = "新增数")
    private Long countNew;

    /**
     * 评论数
     */
    @Excel(name = "评论数")
    private Long countComment;

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

    private String sname;

    private Integer types;

    /**
     *
     */
    @Excel(name = "")
    private Long userId;

    private Long page;

    private Long size;

}
