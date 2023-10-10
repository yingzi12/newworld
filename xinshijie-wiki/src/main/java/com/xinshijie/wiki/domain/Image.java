package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("wiki_image")
@Schema(description = "图片信息表")
public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
    private LocalDateTime createTime;


}
