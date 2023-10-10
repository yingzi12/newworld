package com.xinshijie.common.enums;

/**
 * 元素状态
 */
public enum ChapterStatusEnums {
    NORMAL(1, "正常"),
    AUDIT(3, "待审核"),
    LOCK(2, "锁定"),
    DEL(4, "删除");

    private final Integer code;
    private final String name;

    ChapterStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (ChapterStatusEnums enable : values()) {
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
