package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 元素照片列表
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "ElementImgVo", description = "元素照片列表 ")
public class ElementImgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String url;

    private String fileName;

    private LocalDateTime createTime;

    private String createBy;

    private String createName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;


    /**
     * 标题
     */
    @Schema( description = "标题 ")
    private String title;


    /**
     * 介绍
     */
    @Schema( description = "介绍 ")
    private String introduce;


}
