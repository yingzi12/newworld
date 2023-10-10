package com.xinshijie.common.enums;

public enum DicussTypeEnums {
    FREEDOM(1, "自由讨论"),
    DEAL(2, "建议"),
    ERROR(3, "内容错误"),
    CLOSE(4, "内容缺失"),
    MISS(5, "过多重复"),
    UNCORRELATED(6, "内容不相关"),
    OTHER(7, "其他");
    private final Integer code;
    private final String name;

    DicussTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (DicussTypeEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable.getName();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
