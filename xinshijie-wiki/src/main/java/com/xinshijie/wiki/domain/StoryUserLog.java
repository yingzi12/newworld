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
 * 股市用户日志
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_story_user_log")
@Schema(description = " 股市用户日志")
public class StoryUserLog implements Serializable{
private static final long serialVersionUID=1L;
            @Id
    @TableId(value = "id", type = IdType.AUTO)
        private Long id;
    /**
     * 世界id
     */
                private Integer wid;
    /**
     * 用户id
     */
                private Long userId;
                private String wname;
    /**
     * 操作模块
     */
                private String operate;
    /**
     * 月
     */
                private Integer monthly;
    /**
     * 年
     */
                private Integer year;
    /**
     * 天
     */
                private Integer day;
                private LocalDateTime createTime;
                private Long sid;
                private Integer operateType;
}
