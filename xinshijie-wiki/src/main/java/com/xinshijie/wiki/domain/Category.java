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
 * 元素分类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_category")
@Schema(description = "元素分类")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "名称")
    private String label;

    @Schema(description = "上级id")
    private Long pid;

    @Schema(description = "层级")
    private Integer tier;

    @Schema(description = "分类编码")
    private String code;

    private Long createId;

    private String createName;

    private LocalDateTime createTime;

    private Long updateId;

    private LocalDateTime updateTime;

    private String updateName;

    private String pcode;

    //图片地址
    private String imgUrl;

    //扩展
    private String extra;

    private String pidpath;
}
