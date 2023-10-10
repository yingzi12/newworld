package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "ElementImgDto", description = "元素照片列表 ")
public class ElementImgDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(max = 100, message = " 超出最大长度 100")
    private String url;

    @Size(max = 100, message = " 超出最大长度 100")
    private String fileName;

    private LocalDateTime createTime;

    @Size(max = 100, message = " 超出最大长度 100")
    private String createBy;

    @Size(max = 100, message = " 超出最大长度 100")
    private String createName;

    private Integer wid;

    private Long weid;

    /**
     * 标题
     */
    @Schema( description = "标题 ")
    @Size(max = 100, message = "标题 超出最大长度 100")
    private String title;

    /**
     * 介绍
     */
    @Schema( description = "介绍 ")
    @Size(max = 100, message = "介绍 超出最大长度 100")
    private String introduce;

    private Long page;

    private Long size;
}
