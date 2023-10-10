package com.xinshijie.common.enums;

/**
 * 审核状态
 */
public enum AuditStatusEnums {

    NO(0, "不通过"),
    YES(1, "通过");
    private final Integer code;
    private final String name;

    AuditStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (AuditStatusEnums enable : values()) {
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
