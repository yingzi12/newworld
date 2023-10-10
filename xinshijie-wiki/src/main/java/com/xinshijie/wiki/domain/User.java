package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinshijie.common.annotation.Excel;
import com.xinshijie.common.annotation.Excel.ColumnType;
import com.xinshijie.common.annotation.Excel.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户对象 sys_user
 *
 * @author xinshijie
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@Schema(description = "用户")
public class User {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /**
     * 用户账号
     */
    @Excel(name = "登录名称")
    private String userName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户名称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private LocalDateTime loginDate;


    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /**
     * 更新者
     */
    @TableField
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    private Integer ranks;
    private Integer exp;
    private String tags;
    private Long money;
    private String sign;
    private String remark;

    @Schema(description = "新增章节数")
    private Integer countChapter;
    @Schema(description = "评论数")
    private Integer countComment;
    @Schema(description = "讨论数")
    private Integer countDiscuss;
    @Schema(description = "新增元素数")
    private Integer countElement;
    @Schema(description = "编辑元素数")
    private Integer countEdit;
    @Schema(description = "关注世界数")
    private Integer countFllow;
    @Schema(description = "收藏故事数")
    private Integer countHarding;
    @Schema(description = "点赞")
    private Integer countLike;
    @Schema(description = "新增元素数")
    private Integer countWorld;
    @Schema(description = "编辑章节数")
    private Integer countUpdate;
    @Schema(description = "查看")
    private Integer countSee;
    @Schema(description = "新增故事数")
    private Integer countStory;
    @Schema(description = "收益")
    private Integer incomde;
    @Schema(description = "本月收益")
    private Integer monthlyIncomde;
}
