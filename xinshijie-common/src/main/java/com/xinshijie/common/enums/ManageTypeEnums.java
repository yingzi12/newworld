package com.xinshijie.common.enums;

/**
 * 审核状态
 */
public enum ManageTypeEnums {

    ADVANCED(1, "高级管理员"),
    GENERAL(2, "管理员"),
    AUTHOR(3, "主作家"),
    WRITER(4, "作家");
    private final Integer code;
    private final String name;

    ManageTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (ManageTypeEnums enable : values()) {
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
