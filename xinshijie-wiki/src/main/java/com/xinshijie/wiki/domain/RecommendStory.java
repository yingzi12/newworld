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
 * 推荐的小说，首页使用
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_recommend_story")
@Schema(description = " 推荐的小说，首页使用")
public class RecommendStory implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer wid;
    private String wname;
    private String intro;
    /**
     * 推荐类别
     */
    private Integer recType;
    private String imgUrl;
    /**
     * 评语
     */
    private String comments;
    private LocalDateTime crateTime;
    private Long createBy;
    private String createName;
    /**
     * 推荐排序
     */
    private Integer recSorting;
    /**
     * 小说类别，以,分割
     */
    private String stype;
    /**
     * 小说标签，以,分割
     */
    private String stag;
    /**
     * 是否原创 1 yes 2no
     */
    private Integer isOriginal;
    /**
     * 小说id
     */
    private Long sid;
    /**
     * 小说名称
     */
    private String sname;
}
