package com.xinshijie.common.enums;

/**
 * 元素状态
 */
public enum ContentStatusEnums {
    NORMAL(0, "正常"),
    NEW(1, "新增"),
    LOCK(2, "锁定"),
    EDIT(3, "编辑"),
    DEL(4, "删除");

    private final Integer code;
    private final String name;

    ContentStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (ContentStatusEnums enable : values()) {
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
