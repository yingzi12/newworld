package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 势力.元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "DraftHeadForcesDto", description = "势力.元素头,不同的元素模板对应不同的head ")
public class DraftHeadForcesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer wid;

    private Long weid;

    @Size(max = 200, message = " 超出最大长度 200")
    private String name;

    /**
     * 势力创建时间
     */
    @Schema( description = "势力创建时间 ")
    @Size(max = 100, message = "势力创建时间 超出最大长度 100")
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

    private String extra;

    /**
     * 势力类型
     */
    @Schema( description = "势力类型 ")
    @Size(max = 100, message = "势力类型 超出最大长度 100")
    private String farcesType;

    /**
     * 势力特征
     */
    @Schema( description = "势力特征 ")
    @Size(max = 100, message = "势力特征 超出最大长度 100")
    private String feature;

    /**
     * 势力创建者
     */
    @Schema( description = "势力创建者 ")
    @Size(max = 100, message = "势力创建者 超出最大长度 100")
    private String forceCreator;

    /**
     * 主要成员
     */
    @Schema( description = "主要成员 ")
    @Size(max = 100, message = "主要成员 超出最大长度 100")
    private String mainMembers;

    /**
     * 势力架构
     */
    @Schema( description = "势力架构 ")
    private String architecture;

    /**
     * 势力范围
     */
    @Schema( description = "势力范围 ")
    private String sphereInfluence;

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
