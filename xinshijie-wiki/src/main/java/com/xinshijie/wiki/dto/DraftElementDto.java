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
 * 元素草稿
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素草稿")
public class DraftElementDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "模板类型")
    private Integer softtype;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "是否删除")
    private Integer isDelete;

    private Long createId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createName;

    private Long updateId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateName;

    @Schema(description = "图片路径")
    private String imageUrls;

    @Schema(description = "来源")
    private Long sourceEid;

    @Schema(description = "是否新增")
    private Integer isNew;

    @Schema(description = "修改原因编号")
    private Integer causeNumber;

    @Schema(description = "修改原因理由")
    private String causeContent;

    private String typeCode;

    private String title;

    private String intro;

    private Long page;

    private Long size;

    private String extra;

    @Schema(description = "最后审核与发布时间")
    private LocalDateTime endTime;

    private String pageHtml;
    @Schema(description = "1电脑 2手机")
    private Integer source;
}
