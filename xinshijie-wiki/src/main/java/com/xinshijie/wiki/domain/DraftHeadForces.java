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
 * 势力.元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_draft_head_forces")
@Schema(description = " 势力.元素头,不同的元素模板对应不同的head")
public class DraftHeadForces implements Serializable{
private static final long serialVersionUID=1L;
            @Id
    @TableId(value = "id", type = IdType.AUTO)
        private Long id;
                private Integer wid;
                private Long weid;
                private String name;
    /**
     * 势力创建时间
     */
                private String birthday;
    /**
     * 传记
     */
                private String biography;
    /**
     * 背景
     */
                private String background;
                private String extra;
    /**
     * 势力类型
     */
                private String farcesType;
    /**
     * 势力特征
     */
                private String feature;
    /**
     * 势力创建者
     */
                private String forceCreator;
    /**
     * 主要成员
     */
                private String mainMembers;
    /**
     * 势力架构
     */
                private String architecture;
    /**
     * 势力范围
     */
                private String sphereInfluence;
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
