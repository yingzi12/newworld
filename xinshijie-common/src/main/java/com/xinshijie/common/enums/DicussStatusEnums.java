package com.xinshijie.common.enums;

public enum DicussStatusEnums {
    WAIT(1, "待处理"),
    NOW(2, "已处理"),
    CLOSE(3, "关闭"),
    ;
    private final Integer code;
    private final String name;

    DicussStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (DicussStatusEnums enable : values()) {
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
