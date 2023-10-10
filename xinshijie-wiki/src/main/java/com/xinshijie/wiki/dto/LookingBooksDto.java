package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 寻找书
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
@Data
@Schema(name = "LookingBooksDto", description = "寻找书 ")
public class LookingBooksDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema( description = "id ")
    private Long id;

    /**
     * 书名
     */
    @Schema( description = "书名 ")
    @Size(max = 100, message = "书名 超出最大长度 100")
    private String bookName;

    /**
     * 创建时间
     */
    @Schema( description = "创建时间 ")
    private LocalDateTime createTime;

    /**
     * 这个请求的状态
     */
    @Schema( description = "这个请求的状态 ")
    private Integer status;

    /**
     * 创建人
     */
    @Schema( description = "创建人 ")
    @Size(max = 100, message = "创建人 超出最大长度 100")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema( description = "创建时间 ")
    @Size(max = 100, message = "创建时间 超出最大长度 100")
    private String createName;

    /**
     * 书的介绍
     */
    @Schema( description = "书的介绍 ")
    @Size(max = 300, message = "书的介绍 超出最大长度 300")
    private String intro;

    /**
     * 处理说明
     */
    @Schema( description = "处理说明 ")
    @Size(max = 300, message = "处理说明 超出最大长度 300")
    private String explain;

    /**
     * 我也在寻找
     */
    @Schema( description = "我也在寻找 ")
    private Integer countMeToo;

private Long pageNum;

private Long pageSize;
}
