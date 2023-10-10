package com.xinshijie.wiki.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 图片信息表
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "图片信息表")
public class ImageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "存储方式")
    private String storage;

    @Schema(description = "文件组")
    private Integer groupId;

    @Schema(description = "文件路径")
    private String fileUrl;

    @Schema(description = "文件名称")
    private String fileName;

    private Long fileSize;

    private String fileType;

    private String md5;

    @Schema(description = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String originalFilename;

    private Long page;

    private Long size;
}
