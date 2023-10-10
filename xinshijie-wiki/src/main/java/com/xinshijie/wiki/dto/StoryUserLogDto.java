package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "StoryUserLogDto", description = "股市用户日志 ")
public class StoryUserLogDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 世界id
     */
    @Schema( description = "世界id ")
    private Integer wid;

    /**
     * 用户id
     */
    @Schema( description = "用户id ")
    private Long userId;

    @Size(max = 100, message = " 超出最大长度 100")
    private String wname;

    /**
     * 操作模块
     */
    @Schema( description = "操作模块 ")
    @Size(max = 100, message = "操作模块 超出最大长度 100")
    private String operate;

    /**
     * 月
     */
    @Schema( description = "月 ")
    private Integer monthly;

    /**
     * 年
     */
    @Schema( description = "年 ")
    private Integer year;

    /**
     * 天
     */
    @Schema( description = "天 ")
    private Integer day;

    private LocalDateTime createTime;

    private Long sid;

    private Integer operateType;

private Long pageNum;

private Long pageSize;
}
