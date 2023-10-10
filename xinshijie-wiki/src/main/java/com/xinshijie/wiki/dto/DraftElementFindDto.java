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
public class DraftElementFindDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "模板类型")
    private Integer softtype;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Long createId;

    private Long updateId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "图片路径")
    private String imageUrls;

    @Schema(description = "来源")
    private Long sourceEid;

    @Schema(description = "是否新增")
    private Integer isNew;

    @Schema(description = "修改原因编号")
    private Integer causeNumber;

    private String title;

    private String intro;

    @Schema(description = "审核状态0 没有 1已审核")
    private Integer auditStatus;

    @Schema(description = "最后审核与发布时间")
    private LocalDateTime endTime;

    private Long page;

    private Long size;

}
