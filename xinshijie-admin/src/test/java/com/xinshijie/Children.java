package com.xinshijie;

import lombok.Data;

import java.util.List;

@Data
public class Children {
    private String ch_ext;
    private Integer depth;
    private Integer entry_limit;
    private List<Children> children;
    private Boolean hidden;
    private Long id;
    private List<Detail> list;
    private String name;
    private String parent_id;
}
