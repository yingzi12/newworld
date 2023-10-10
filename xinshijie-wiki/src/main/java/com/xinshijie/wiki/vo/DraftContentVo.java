package com.xinshijie.wiki.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 元素内容草稿
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素内容草稿")
public class DraftContentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long eid;

    private String title;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "世界id")
    private Integer wid;

    private Long createId;

    private String createName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "元素内容id")
    private Long sourceEcid;

    @Schema(description = "修改记录")
    private String diff;

    @Schema(description = "元素id")
    private Long sourceEid;

    @Schema(description = "序号")
    private String serial;

    @Schema(description = "是否新增")
    private Integer isDelete;

    @Schema(description = "是否删除")
    private Integer isNew;

    @Schema(description = "是否修改")
    private Integer isUpdate;

    @Schema(description = "元素内容id")
    private Long souceCid;

    @Schema(description = "扩展")
    private String extra;

    private Long page;

    private Long size;
}
