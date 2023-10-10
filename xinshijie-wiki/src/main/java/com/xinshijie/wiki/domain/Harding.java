package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 收藏
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_harding")
@Schema(description = "收藏")
public class Harding implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "故事id")
    private Long sid;

    @Schema(description = "故事名称")
    private String sname;

    @Schema(description = "用户id")
    private Long createId;

    @Schema(description = "用户名称")
    private String createName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
