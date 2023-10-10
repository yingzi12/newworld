package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 地理,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_draft_head_geography")
@Schema(description = " 地理,元素头,不同的元素模板对应不同的head")
public class DraftHeadGeography implements Serializable{
private static final long serialVersionUID=1L;
            @Id
    @TableId(value = "id", type = IdType.AUTO)
        private Long id;
                private Integer wid;
                private Long weid;
                private String name;
    /**
     * 传记
     */
                private String biography;
                private String extra;
    /**
     * 种类
     */
                private String kind;
    /**
     * 特征
     */
                private String feature;
    /**
     * 气候
     */
                private String climate;
    /**
     * 生活种族
     */
                private String livingRace;
    /**
     * 所属势力
     */
                private String affiliatedForces;
    /**
     * 主要人物
     */
                private String mainCharacter;
    /**
     * 特产
     */
                private String specialty;
    /**
     * 环境
     */
                private String environment;
    /**
     * 简单地图url
     */
                private String imgUrl;
    /**
     * 所属地区
     */
                private String districtBelong;
    /**
     * 元素id
     */
                private Long districtBelongId;
                private Long createId;
                private String createName;
                private LocalDateTime createTime;
                private Long updateId;
                private String updateName;
                private LocalDateTime updateTime;
    /**
     * 元素head内容id
     */
                private Long sourceEhid;
    /**
     * 修改记录
     */
                private String diff;
    /**
     * 元素id
     */
                private Long sourceEid;
    /**
     * 序号
     */
                private String serial;
    /**
     * 是否新增
     */
                private Integer isDelete;
    /**
     * 是否删除
     */
                private Integer isNew;
    /**
     * 是否修改
     */
                private Integer isUpdate;
    /**
     * 元素内容id
     */
                private Long souceCid;
}
