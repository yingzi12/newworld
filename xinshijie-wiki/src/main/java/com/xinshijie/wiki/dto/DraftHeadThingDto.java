package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "DraftHeadThingDto", description = "物品/材料,元素头,不同的元素模板对应不同的head ")
public class DraftHeadThingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer wid;

    private Long weid;

    @Size(max = 200, message = " 超出最大长度 200")
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
    @Size(max = 100, message = "种类 超出最大长度 100")
    private String kind;

    /**
     * 特征
     */
    @Schema( description = "特征 ")
    @Size(max = 100, message = "特征 超出最大长度 100")
    private String feature;

    /**
     * 特殊说明
     */
    @Schema( description = "特殊说明 ")
    @Size(max = 100, message = "特殊说明 超出最大长度 100")
    private String specialInstructions;

    /**
     * 用途
     */
    @Schema( description = "用途 ")
    @Size(max = 100, message = "用途 超出最大长度 100")
    private String purpose;

    /**
     * 起源/产地
     */
    @Schema( description = "起源/产地 ")
    @Size(max = 100, message = "起源/产地 超出最大长度 100")
    private String origin;

    /**
     * 材料
     */
    @Schema( description = "材料 ")
    @Size(max = 100, message = "材料 超出最大长度 100")
    private String material;

    /**
     * 制作/加工
     */
    @Schema( description = "制作/加工 ")
    @Size(max = 100, message = "制作/加工 超出最大长度 100")
    private String make;

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
