package com.xinshijie.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 书架
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_bookshelf")
@Schema(description = "书架")
public class Bookshelf implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String userName;

    private Integer wid;

    @Schema(description = "小说id")
    private Long sid;

    private String createTime;

    private String updateTime;

    @Schema(description = "最后看的章节id")
    private Long lastChapterId;

    @Schema(description = "最后看的章节名称")
    private String lastChapterName;

    @Schema(description = "是否有更新")
    private Integer isNew;


}
