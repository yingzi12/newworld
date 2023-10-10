package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum ApplySatusEnums {
    WAIT(1,"申请中"),
    AUDIT(2,"申请通过"),
    FAILURE(3,"申请不通过"),
    CLEAN(4,"取消申请"),
    ;
    private Integer code;
    private String name;

    ApplySatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
