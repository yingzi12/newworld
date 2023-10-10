package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "WorldChronologyDto", description = "世界年表 ")
public class WorldChronologyDto implements Serializable {


    private Integer wid;

    @Size(max = 100, message = " 超出最大长度 100")
    private String wname;

    /**
     * 发生时间
     */
    @Schema( description = "发生时间 ")
    @Size(max = 100, message = "发生时间 超出最大长度 100")
    private String timeOccurrence;

    private LocalDateTime createTime;

    @Size(max = 100, message = " 超出最大长度 100")
    private String createBy;

    @Size(max = 100, message = " 超出最大长度 100")
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
    @Size(max = 100, message = "说明 超出最大长度 100")
    private String content;

    /**
     * 标题
     */
    @Schema( description = "标题 ")
    @Size(max = 100, message = "标题 超出最大长度 100")
    private String title;

    private Long pageNum;

    private Long pageSize;
}
