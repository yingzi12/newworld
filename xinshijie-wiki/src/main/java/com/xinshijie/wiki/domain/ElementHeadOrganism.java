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
 * 生物,元素头,不同的元素模板对应不同的head
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_element_head_organism")
@Schema(description = " 生物,元素头,不同的元素模板对应不同的head")
public class ElementHeadOrganism implements Serializable {

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
         * 生长/生活习惯
         */
        private String growthHabits;

        /**
         * 分布范围
         */
        private String distributionArea;

        /**
         * 特殊说明
         */
        private String specialInstructions;
}
