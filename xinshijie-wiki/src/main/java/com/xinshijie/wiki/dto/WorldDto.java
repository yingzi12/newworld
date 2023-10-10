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
 * 世界
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "返回世界详细")
public class WorldDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "说明")
    private String description;

    @Schema(description = "分类")
    private Integer types;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "等级")
    private Integer ranks;

    @Schema(description = "经验值")
    private Integer vitality;

    @Schema(description = "最新的元素")
    private String updateNewElement;

    @Schema(description = "最新的元素id")
    private Long updateNewElementId;

    @Schema(description = "得分")
    private Integer scores;

    @Schema(description = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建人id")
    private Long createId;

    @Schema(description = "更新人名称")
    private String updateName;

    @Schema(description = "更新人id")
    private Long updateId;

    @Schema(description = "发布之后不能修改名称")
    private Integer status;

    @Schema(description = "图片路径")
    private String imgUrl;

    @Schema(description = "元素最新更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateNewElementTime;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "是否私有")
    private Integer isPrivate;

    private Long page;

    private Long size;
    @Schema(description = "管理员")
    private Integer countAdmin;
    @Schema(description = "分类数")
    private Integer countAddType;
    @Schema(description = "评论数")
    private Integer countComment;
    private Integer countDiscuss;
    @Schema(description = "元素数")
    private Integer countElement;
    @Schema(description = "编辑数")
    private Integer countEdit;
    @Schema(description = "关注数")
    private Integer countFllow;
    @Schema(description = "点赞")
    private Integer countLike;
    @Schema(description = "居民数")
    private Integer countResident;
    @Schema(description = "查看")
    private Integer countSee;
    @Schema(description = "故事数")
    private Integer countStory;


}

