package com.xinshijie.wiki.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class FeedbackDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer types;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "title")
    private String title;
    @Schema(description = "说明")
    private String content;

    private Long createId;
    private LocalDateTime createTime;

    @Schema(description = "状态")
    private Integer status;


    @Schema(description = "反馈")
    private String reply;
    private Long page;

    private Long size;

    private String uuid;

    private String code;

}
