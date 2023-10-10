package com.xinshijie.wiki.dto;

import com.xinshijie.common.annotation.Excel;
import com.xinshijie.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 元素对象 wiki_element
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class DraftElementUpdateDto extends BaseEntity {
    private Long id;

    private Integer wid;

    private String intro;

    private String title;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private Integer version;

    private Long userId;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tags;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Integer isDelete;

    @Schema(description = "审核原因编号")
    private Integer auditNumber;

    @Schema(description = "审核说明")
    private String auditContent;

    private List<Long> categoryList;

    private List<DraftContentUpdateDto> contentList;

    private List<Long> contentIdList;

    private String extra;

    @Schema(description = "最后审核与发布时间")
    private LocalDateTime endTime;

    private String pageHtml;
    @Schema(description = "1电脑 2手机")
    private Integer source;
}
