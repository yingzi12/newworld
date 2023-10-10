package com.xinshijie.wiki.dto;

import com.xinshijie.common.core.dto.BaseEntityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 世界
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "添加世界")
public class StoryAddDto extends BaseEntityDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;

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

    @Schema(description = "图片路径")
    private String imgUrl;

    private String description;

    private Integer wid;

    @Schema(description = "世界名称")
    private String wname;


}
