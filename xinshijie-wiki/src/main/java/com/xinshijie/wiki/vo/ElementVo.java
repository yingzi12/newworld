package com.xinshijie.wiki.vo;


import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xinshijie.wiki.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 元素
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "元素")
public class ElementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "世界id")
    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;

    @Schema(description = "模板类型")
    private Integer softtype;

    @Schema(description = "查看")
    private Integer countSee;

    @Schema(description = "编辑")
    private Integer countEdit;

    @Schema(description = "评论")
    private Integer countComment;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "状态 0正常")
    private Integer status;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateName;

    @Schema(description = "图片路径")
    private String imageUrls;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "说明")
    private String intro;

    @Schema(description = "分类id$$分类名称,以,分割")
    private String idLabels;
    private String extra;
    private Long page;

    private Long size;

    private List<CategoryVo> categoryList;

    private List<ElementContentVo> contentList;

    private List<String> cnameList;

    private String pageHtml;
    @Schema(description = "1电脑 2手机")
    private Integer source;

    private ElementHeadForces forces;
    private ElementHeadGeography geography;
    private ElementHeadOrganism organismS;
    private ElementHeadRace race;
    private ElementHeadRole role;
    private ElementHeadThing thing;
}
