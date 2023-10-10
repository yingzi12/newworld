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
 * 讨论回复表
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_discuss_comment")
@Schema(description = "讨论回复表")
public class DiscussComment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "上级记录回复id")
    private Long upid;

    @Schema(description = "评论内容")
    private String comment;

    @Schema(description = "评论回复,被评论人的内容")
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

    private LocalDateTime createTime;

    private Long updateId;

    private String updateName;

    private LocalDateTime updateTime;

    @Schema(description = "元素id")
    private Long eid;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private Integer types;

    @Schema(description = "讨论主题id")
    private Long did;

    @Schema(description = "层级为1回复的id")
    private Long pid;

    private String circleUrl;

    private String nickname;
    /**
     * ,被评论人
     */
    private String replyNickname;

    private Long sid;

    private String sname;
    private Integer source;
    private Long replyUserId;


}
