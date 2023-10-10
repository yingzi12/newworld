package com.xinshijie.wiki.dto;

import lombok.Data;

@Data
public class ElementCategoryUpdateDto {
    private Long id;
    private String label;
    private Long pid;
    private String code;
    private String value;
}
