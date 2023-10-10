package com.xinshijie.wiki.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 意见反馈
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "意见反馈")
public class FeedbackVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "说明")
    private String content;

    private String createTime;

    @Schema(description = "状态")
    private Integer status;

    private Long createId;

    @Schema(description = "反馈")
    private String reply;
}
