package com.xinshijie.wiki.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 来源
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "来源")
public class StorySourceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "来源类型")
    private Integer types;

    @Schema(description = "来源名称")
    private String name;

    @Schema(description = "链接地址")
    private String url;

    @Schema(description = "备注")
    private String remark;

    private Long createId;

    private String createName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Integer wid;
    private Long sid;
}
