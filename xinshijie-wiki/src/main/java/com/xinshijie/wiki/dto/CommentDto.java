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
 * 评论表
 * </p>
 *
 * @author zx
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "评论表")
public class CommentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "评论id")
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "上级记录回复id")
    private Long upid;

    @Schema(description = "评论内容")
    private String comment;

    @Schema(description = "评论回复")
    private String reply;

    @Schema(description = "回复的层级")
    private Integer ranks;

    @Schema(description = "点赞数")
    private Integer countLike;

    @Schema(description = "回复数")
    private Integer countReply;

    @Schema(description = "不同意数")
    private Integer countDisagree;

    @Schema(description = "状态")
    private Integer status;

    private Long createId;

    private String createName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateId;

    private String updateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "元素id")
    private Long eid;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private Integer types;

    @Schema(description = "层级为1回复的id")
    private Long pid;

    private Long sid;

    private String sname;

    private Long page;

    private Long size;

    private Integer source;
    @Schema(description = "是否推荐 1推荐,2不推荐")
    private Integer isRecommend;
}
