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
 * 元素分类对应表
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_element_category")
@Schema(description = "元素分类对应表")
public class ElementCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "元素id")
    private Long eid;

    @Schema(description = "分类id")
    private Long cid;

    private Long createId;

    private String createName;

    private LocalDateTime createTime;

    private Long updateId;

    private LocalDateTime updateTime;

    private String updateName;


}
