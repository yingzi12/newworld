package com.xinshijie.common.enums;

/**
 * 元素状态
 */
public enum ElementStatusEnums {
    NORMAL(1, "正常"),
    LOCK(2, "锁定"),
    AUDIT(3, "待审核"),
    DEL(4, "删除");

    private final Integer code;
    private final String name;

    ElementStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (ElementStatusEnums enable : values()) {
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
