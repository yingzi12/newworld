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
 * 物品/材料,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_element_head_thing")
@Schema(description = " 物品/材料,元素头,不同的元素模板对应不同的head")
public class ElementHeadThing implements Serializable {

private static final long serialVersionUID = 1L;

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
         * 特殊说明
         */
        private String specialInstructions;

        /**
         * 用途
         */
        private String purpose;

        /**
         * 起源/产地
         */
        private String origin;

        /**
         * 材料
         */
        private String material;

        /**
         * 制作/加工
         */
        private String make;
}
