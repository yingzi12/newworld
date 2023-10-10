package com.xinshijie.wiki.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecommendStoryAddDto {
    private Long sid;

    /**
     * 推荐类别
     */
    @Schema( description = "推荐类别 ")
    private Integer recType;

    /**
     * 评语
     */
    @Schema( description = "评语 ")
    @Size(max = 100, message = "评语 超出最大长度 100")
    private String comments;


    /**
     * 推荐排序
     */
    @Schema( description = "推荐排序 ")
    private Integer recSorting;

}
