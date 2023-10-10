package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum StoryTypeEnums {
    t6(5,"其他"),
    ;
    private Integer code;
    private String name;

    StoryTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (StoryTypeEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable.getName();
            }
        }
        return "";
    }
}
