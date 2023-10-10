package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 世界年表
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "WorldChronologyVo", description = "世界年表 ")
public class WorldChronologyVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    private String wname;


    /**
     * 发生时间
     */
    @Schema( description = "发生时间 ")
    private String timeOccurrence;

    private LocalDateTime createTime;

    private String createBy;

    private String createName;


    /**
     * 排序
     */
    @Schema( description = "排序 ")
    private Integer sordNumber;


    /**
     * 说明
     */
    @Schema( description = "说明 ")
    private String content;


    /**
     * 标题
     */
    @Schema( description = "标题 ")
    private String title;


}
