package com.xinshijie.wiki.vo;

import lombok.Data;

@Data
public class DiffVo {
    private String oldTitle;
    private String newTitle;
    private String oldContent;
    private String newContent;
}
