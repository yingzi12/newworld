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
 * 寻找书
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_looking_books")
@Schema(description = " 寻找书")
public class LookingBooks implements Serializable{
private static final long serialVersionUID=1L;
    /**
     * id
     */
            @Id
    @TableId(value = "id", type = IdType.AUTO)
        private Long id;
    /**
     * 书名
     */
                private String bookName;
    /**
     * 创建时间
     */
                private LocalDateTime createTime;
    /**
     * 这个请求的状态
     */
                private Integer status;
    /**
     * 创建人
     */
                private String createBy;
    /**
     * 创建时间
     */
                private String createName;
    /**
     * 书的介绍
     */
                private String intro;
    /**
     * 处理说明
     */
                private String explain;
    /**
     * 我也在寻找
     */
                private Integer countMeToo;
}
