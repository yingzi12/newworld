package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
@Schema(name = "LookingBooksVo", description = "寻找书 ")
public class LookingBooksVo implements Serializable{

private static final long serialVersionUID=1L;


    /**
     * id
     */
    @Schema(description = "id ")
            @JsonSerialize(using = ToStringSerializer.class)
private Long id;


    /**
     * 书名
     */
    @Schema(description = "书名 ")
        private String bookName;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间 ")
        private LocalDateTime createTime;


    /**
     * 这个请求的状态
     */
    @Schema(description = "这个请求的状态 ")
        private Integer status;


    /**
     * 创建人
     */
    @Schema(description = "创建人 ")
        private String createBy;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间 ")
        private String createName;


    /**
     * 书的介绍
     */
    @Schema(description = "书的介绍 ")
        private String intro;


    /**
     * 处理说明
     */
    @Schema(description = "处理说明 ")
        private String explain;


    /**
     * 我也在寻找
     */
    @Schema(description = "我也在寻找 ")
        private Integer countMeToo;


}
