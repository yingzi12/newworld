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
 * 世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_blacklist")
@Schema(description = "世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作")
public class Blacklist implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "用户id")
    private Long createId;

    @Schema(description = "用户名称")
    private String createName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    private Long userId;

    private String userName;

    @Schema(description = "状态 1表示永久")
    private Integer status;

    @Schema(description = "封禁时间")
    private LocalDateTime endTime;

    @Schema(description = "说明")
    private String explain;


}
