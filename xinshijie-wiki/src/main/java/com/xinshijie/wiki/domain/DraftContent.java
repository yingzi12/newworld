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
 * 元素内容草稿
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_draft_content")
@Schema(description = "元素内容草稿")
public class DraftContent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private LocalDateTime createTime;

    private Long updateId;

    private String updateName;

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

    @Schema(description = "扩展")
    private String extra;

}
