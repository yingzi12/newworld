package com.xinshijie.wiki.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChapterIntroVo {
    @Schema(description = "名称")
    private String title;

    @Schema(description = "状态 1草稿 2发布 3隐藏")
    private Integer status;

    @Schema(description = "章节编号")
    private Long serialNumber;

    private Integer wid;

    private Long sid;

    private String sname;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "上级")
    private Long pid;

    @Schema(description = "是否收费")
    private Integer isVip;

    @Schema(description = "内容")
    private String content;

    private Long upId;

    private Long downId;

    private ChapterIntroVo previous;

    private ChapterIntroVo next;

}
