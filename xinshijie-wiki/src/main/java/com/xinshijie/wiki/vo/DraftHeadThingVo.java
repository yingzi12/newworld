package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 物品/材料,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "DraftHeadThingVo", description = "物品/材料,元素头,不同的元素模板对应不同的head ")
public class DraftHeadThingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;

    private String name;


    /**
     * 传记
     */
    @Schema( description = "传记 ")
    private String biography;

    private String extra;


    /**
     * 种类
     */
    @Schema( description = "种类 ")
    private String kind;


    /**
     * 特征
     */
    @Schema( description = "特征 ")
    private String feature;


    /**
     * 特殊说明
     */
    @Schema( description = "特殊说明 ")
    private String specialInstructions;


    /**
     * 用途
     */
    @Schema( description = "用途 ")
    private String purpose;


    /**
     * 起源/产地
     */
    @Schema( description = "起源/产地 ")
    private String origin;


    /**
     * 材料
     */
    @Schema( description = "材料 ")
    private String material;


    /**
     * 制作/加工
     */
    @Schema( description = "制作/加工 ")
    private String make;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    private String createName;

    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    private String updateName;

    private LocalDateTime updateTime;


    /**
     * 元素head内容id
     */
    @Schema( description = "元素head内容id ")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceEid;


    /**
     * 序号
     */
    @Schema( description = "序号 ")
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long souceCid;


}
