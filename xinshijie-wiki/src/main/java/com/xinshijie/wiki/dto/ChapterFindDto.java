package com.xinshijie.wiki.dto;


import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 故事章节
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "故事章节")
public class ChapterFindDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;


    @Schema(description = "名称")
    private String title;

    @SchemaProperty()
    private Integer status;

    @Schema(description = "章节编号")
    private Long serialNumber;

    private LocalDateTime createTime;

    private String createName;

    private Long createId;

    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "上级")
    private Long pid;

    @Schema(description = "层级 1主标题 2小标题")
    private String level;

    private Integer wid;

    private Long sid;

    private Long page;

    private Long size;


}
