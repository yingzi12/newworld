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
 * 角色元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_element_head_role")
@Schema(description = " 角色元素头,不同的元素模板对应不同的head")
public class ElementHeadRole implements Serializable {

private static final long serialVersionUID = 1L;

        @Id
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;

        private Integer wid;

        private Long weid;


        private String name;

        /**
         * 生日
         */
        private String birthday;

        /**
         * 传记
         */
        private String biography;

        /**
         * 种族
         */
        private String race;

        /**
         * 种族对应的元素id
         */
        private Long raceId;

        /**
         * 势力
         */
        private String forces;

        /**
         * 势力对应的元素id
         */
        private Long forcesId;

        /**
         * 别名
         */
        private String alias;

        /**
         * 称号
         */
        private String title;

        /**
         * 出生地
         */
        private String placeBirth;

        /**
         * 年龄
         */
        private String age;

        /**
         * 死亡日期
         */
        private String dateDeath;

        /**
         * 死亡原因
         */
        private String causeDeath;

        /**
         * 性格
         */
        private String character;

        /**
         * 成就
         */
        private String achievement;

        /**
         * 身份
         */
        private String identity;

        /**
         * 母亲
         */
        private String mother;

        /**
         * 父亲
         */
        private String father;

        /**
         * 子女
         */
        private String child;

        /**
         * 女儿
         */
        private String daughter;

        /**
         * 儿子
         */
        private String son;

        /**
         * 家庭
         */
        private String family;

        /**
         * 背景
         */
        private String background;

        private String extra;

        /**
         * 特殊说明
         */
        private String specialInstructions;
}
