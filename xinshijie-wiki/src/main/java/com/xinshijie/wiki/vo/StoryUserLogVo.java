package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 股市用户日志
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
@Data
@Schema(name = "StoryUserLogVo", description = "股市用户日志 ")
public class StoryUserLogVo implements Serializable{

    private static final long serialVersionUID=1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;


    /**
     * 世界id
     */
    @Schema(description = "世界id ")
    private Integer wid;


    /**
     * 用户id
     */
    @Schema(description = "用户id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String wname;


    /**
     * 操作模块
     */
    @Schema(description = "操作模块 ")
    private String operate;


    /**
     * 月
     */
    @Schema(description = "月 ")
    private Integer monthly;


    /**
     * 年
     */
    @Schema(description = "年 ")
    private Integer year;


    /**
     * 天
     */
    @Schema(description = "天 ")
    private Integer day;

    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sid;

    private Integer operateType;


}
