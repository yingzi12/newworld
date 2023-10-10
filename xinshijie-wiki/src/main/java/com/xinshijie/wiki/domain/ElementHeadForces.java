package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

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
@TableName("wiki_element_head_forces")
@Schema(description = " 势力.元素头,不同的元素模板对应不同的head")
public class ElementHeadForces implements Serializable {

private static final long serialVersionUID = 1L;

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
}
