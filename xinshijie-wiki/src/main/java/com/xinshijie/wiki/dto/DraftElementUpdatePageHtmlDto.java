package com.xinshijie.wiki.dto;

import com.xinshijie.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 元素对象 wiki_element
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Data
public class DraftElementUpdatePageHtmlDto extends BaseEntity {
    private Long id;

    private Integer wid;

    private Long userId;

    private String pageHtml;


}
