package com.xinshijie.wiki.dto;

import com.xinshijie.common.core.page.PageDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 世界对象 wiki_world
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class StoryFindDto extends PageDomain {
    private static final long serialVersionUID = 1L;
    @Schema(description = "id")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    private Long userId;


    @Schema(description = "分类")
    private Integer types;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "发布之后不能修改名称")
    private Integer status;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "是否私有")
    private Integer isPrivate;

    private Integer isAdmin;
    private Integer auditNumber;
    private String tags;

    private Integer wid;

    private String wname;

    private Long page;

    private Long size;

    private Integer countFllow;

}
