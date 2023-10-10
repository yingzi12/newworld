package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;
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
@Schema(name = "DraftHeadRoleDto", description = "角色元素头,不同的元素模板对应不同的head ")
public class DraftHeadRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer wid;

    private Long weid;

    @Size(max = 200, message = " 超出最大长度 200")
    private String name;

    /**
     * 生日
     */
    @Schema( description = "生日 ")
    @Size(max = 100, message = "生日 超出最大长度 100")
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
    @Size(max = 100, message = "种族 超出最大长度 100")
    private String race;

    /**
     * 种族对应的元素id
     */
    @Schema( description = "种族对应的元素id ")
    private Long raceId;

    /**
     * 势力
     */
    @Schema( description = "势力 ")
    @Size(max = 100, message = "势力 超出最大长度 100")
    private String forces;

    /**
     * 势力对应的元素id
     */
    @Schema( description = "势力对应的元素id ")
    private Long forcesId;

    /**
     * 别名
     */
    @Schema( description = "别名 ")
    @Size(max = 100, message = "别名 超出最大长度 100")
    private String alias;

    /**
     * 称号
     */
    @Schema( description = "称号 ")
    @Size(max = 100, message = "称号 超出最大长度 100")
    private String title;

    /**
     * 出生地
     */
    @Schema( description = "出生地 ")
    @Size(max = 100, message = "出生地 超出最大长度 100")
    private String placeBirth;

    /**
     * 年龄
     */
    @Schema( description = "年龄 ")
    @Size(max = 100, message = "年龄 超出最大长度 100")
    private String age;

    /**
     * 死亡日期
     */
    @Schema( description = "死亡日期 ")
    @Size(max = 100, message = "死亡日期 超出最大长度 100")
    private String dateDeath;

    /**
     * 死亡原因
     */
    @Schema( description = "死亡原因 ")
    @Size(max = 100, message = "死亡原因 超出最大长度 100")
    private String causeDeath;

    /**
     * 性格
     */
    @Schema( description = "性格 ")
    @Size(max = 100, message = "性格 超出最大长度 100")
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
    @Size(max = 100, message = "身份 超出最大长度 100")
    private String identity;

    /**
     * 母亲
     */
    @Schema( description = "母亲 ")
    @Size(max = 100, message = "母亲 超出最大长度 100")
    private String mother;

    /**
     * 父亲
     */
    @Schema( description = "父亲 ")
    @Size(max = 100, message = "父亲 超出最大长度 100")
    private String father;

    /**
     * 子女
     */
    @Schema( description = "子女 ")
    @Size(max = 100, message = "子女 超出最大长度 100")
    private String child;

    /**
     * 女儿
     */
    @Schema( description = "女儿 ")
    @Size(max = 100, message = "女儿 超出最大长度 100")
    private String daughter;

    /**
     * 儿子
     */
    @Schema( description = "儿子 ")
    @Size(max = 100, message = "儿子 超出最大长度 100")
    private String son;

    /**
     * 家庭
     */
    @Schema( description = "家庭 ")
    @Size(max = 100, message = "家庭 超出最大长度 100")
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
    @Size(max = 100, message = "特殊说明 超出最大长度 100")
    private String specialInstructions;

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
