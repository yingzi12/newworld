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
 * 元素照片列表
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_element_img")
@Schema(description = " 元素照片列表")
public class ElementImg implements Serializable {

private static final long serialVersionUID = 1L;

        @Id
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;

        private String url;

        private String fileName;

        private LocalDateTime createTime;

        private String createBy;

        private String createName;

        private Long size;

        private Integer wid;

        private Long weid;

        /**
         * 标题
         */
        private String title;

        /**
         * 介绍
         */
        private String introduce;
}
