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
 * 推荐的世界，首页使用
 * </p>
 *
 * @author 作者
 * @since 2023-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wiki_recommend_world")
@Schema(description = " 推荐的世界，首页使用")
public class RecommendWorld implements Serializable{
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
     * 世界类别，以,分割
     */
    private String wtype;
    /**
     * 世界标签，以,分割
     */
    private String wtag;
    /**
     * 是否原创 1 yes 2no
     */
    private Integer isOriginal;
}
