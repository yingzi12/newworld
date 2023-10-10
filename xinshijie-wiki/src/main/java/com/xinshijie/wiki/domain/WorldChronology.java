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
 * 世界年表
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_world_chronology")
@Schema(description = " 世界年表")
public class WorldChronology implements Serializable {

private static final long serialVersionUID = 1L;

        @Id
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;

        private Integer wid;

        private String wname;

        /**
         * 发生时间
         */
        private String timeOccurrence;

        private LocalDateTime createTime;

        private String createBy;

        private String createName;

        /**
         * 排序
         */
        private Integer sordNumber;

        /**
         * 说明
         */
        private String content;

        /**
         * 标题
         */
        private String title;
}
