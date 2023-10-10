package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 角色元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "ElementHeadRoleVo", description = "角色元素头,不同的元素模板对应不同的head ")
public class ElementHeadRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer wid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long weid;

    private String name;


    /**
     * 生日
     */
    @Schema( description = "生日 ")
    private String birthday;


    /**
     * 传记
     */
    @Schema( description = "传记 ")
    private String biography;


    /**
     * 种族
     */
    @Schema( description = "种族 ")
    private String race;


    /**
     * 种族对应的元素id
     */
    @Schema( description = "种族对应的元素id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long raceId;


    /**
     * 势力
     */
    @Schema( description = "势力 ")
    private String forces;


    /**
     * 势力对应的元素id
     */
    @Schema( description = "势力对应的元素id ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long forcesId;


    /**
     * 别名
     */
    @Schema( description = "别名 ")
    private String alias;


    /**
     * 称号
     */
    @Schema( description = "称号 ")
    private String title;


    /**
     * 出生地
     */
    @Schema( description = "出生地 ")
    private String placeBirth;


    /**
     * 年龄
     */
    @Schema( description = "年龄 ")
    private String age;


    /**
     * 死亡日期
     */
    @Schema( description = "死亡日期 ")
    private String dateDeath;


    /**
     * 死亡原因
     */
    @Schema( description = "死亡原因 ")
    private String causeDeath;


    /**
     * 性格
     */
    @Schema( description = "性格 ")
    private String character;


    /**
     * 成就
     */
    @Schema( description = "成就 ")
    private String achievement;


    /**
     * 身份
     */
    @Schema( description = "身份 ")
    private String identity;


    /**
     * 母亲
     */
    @Schema( description = "母亲 ")
    private String mother;


    /**
     * 父亲
     */
    @Schema( description = "父亲 ")
    private String father;


    /**
     * 子女
     */
    @Schema( description = "子女 ")
    private String child;


    /**
     * 女儿
     */
    @Schema( description = "女儿 ")
    private String daughter;


    /**
     * 儿子
     */
    @Schema( description = "儿子 ")
    private String son;


    /**
     * 家庭
     */
    @Schema( description = "家庭 ")
    private String family;


    /**
     * 背景
     */
    @Schema( description = "背景 ")
    private String background;

    private String extra;


    /**
     * 特殊说明
     */
    @Schema( description = "特殊说明 ")
    private String specialInstructions;


}
