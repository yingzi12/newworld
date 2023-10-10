package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnums {
    ALL(0,"所有"),
    YES(1,"允许/可以/是"),
    NO(2,"拒绝/不可以/否"),
        ;
    private Integer code;
    private String name;

    StatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
