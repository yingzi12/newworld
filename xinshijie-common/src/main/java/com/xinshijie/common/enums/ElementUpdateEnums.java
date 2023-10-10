package com.xinshijie.common.enums;

/**
 * 审核状态
 */
public enum ElementUpdateEnums {

    OTHER(1, "新增元素"),
    NEW(2, "其他"),
    ADD(3, "添加内容"),
    UPDATE(4, "修改错误"),
    DEWEIGHT(5, "去重复内容"),

    ;
    private final Integer code;
    private final String name;

    ElementUpdateEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (ElementUpdateEnums enable : values()) {
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
