package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 种族,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "DraftHeadRaceDto", description = "种族,元素头,不同的元素模板对应不同的head ")
public class DraftHeadRaceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer wid;

    private Long weid;

    @Size(max = 200, message = " 超出最大长度 200")
    private String name;

    /**
     * 创建时间
     */
    @Schema( description = "创建时间 ")
    @Size(max = 100, message = "创建时间 超出最大长度 100")
    private String birthday;

    /**
     * 传记
     */
    @Schema( description = "传记 ")
    private String biography;

    /**
     * 背景
     */
    @Schema( description = "背景 ")
    private String background;

    /**
     * 其他
     */
    @Schema( description = "其他 ")
    private String extra;

    /**
     * 种类分类
     */
    @Schema( description = "种类分类 ")
    @Size(max = 100, message = "种类分类 超出最大长度 100")
    private String raceType;

    /**
     * 种族特征
     */
    @Schema( description = "种族特征 ")
    @Size(max = 100, message = "种族特征 超出最大长度 100")
    private String feature;

    /**
     * 主要成员
     */
    @Schema( description = "主要成员 ")
    @Size(max = 100, message = "主要成员 超出最大长度 100")
    private String mainMembers;

    /**
     * 势力范围
     */
    @Schema( description = "势力范围 ")
    private String sphereInfluence;

    /**
     * 分布范围
     */
    @Schema( description = "分布范围 ")
    @Size(max = 100, message = "分布范围 超出最大长度 100")
    private String releaseScope;

    /**
     * 主要聚集地
     */
    @Schema( description = "主要聚集地 ")
    @Size(max = 100, message = "主要聚集地 超出最大长度 100")
    private String mainGatheringPlace;

    /**
     * 主要势力
     */
    @Schema( description = "主要势力 ")
    @Size(max = 100, message = "主要势力 超出最大长度 100")
    private String mainForces;

    /**
     * 传说
     */
    @Schema( description = "传说 ")
    @Size(max = 100, message = "传说 超出最大长度 100")
    private String legend;

    /**
     * 习俗
     */
    @Schema( description = "习俗 ")
    @Size(max = 100, message = "习俗 超出最大长度 100")
    private String custom;

    private Long createId;

    @Size(max = 50, message = " 超出最大长度 50")
    private String createName;

    private LocalDateTime createTime;

    private Long updateId;

    @Size(max = 50, message = " 超出最大长度 50")
    private String updateName;

    private LocalDateTime updateTime;

    /**
     * 元素head内容id
     */
    @Schema( description = "元素head内容id ")
    private Long sourceEhid;

    /**
     * 修改记录
     */
    @Schema( description = "修改记录 ")
    private String diff;

    /**
     * 元素id
     */
    @Schema( description = "元素id ")
    private Long sourceEid;

    /**
     * 序号
     */
    @Schema( description = "序号 ")
    @Size(max = 100, message = "序号 超出最大长度 100")
    private String serial;

    /**
     * 是否新增
     */
    @Schema( description = "是否新增 ")
    private Integer isDelete;

    /**
     * 是否删除
     */
    @Schema( description = "是否删除 ")
    private Integer isNew;

    /**
     * 是否修改
     */
    @Schema( description = "是否修改 ")
    private Integer isUpdate;

    /**
     * 元素内容id
     */
    @Schema( description = "元素内容id ")
    private Long souceCid;

    private Long pageNum;

    private Long pageSize;
}
