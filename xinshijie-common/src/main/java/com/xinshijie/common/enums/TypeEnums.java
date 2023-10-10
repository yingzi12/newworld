package com.xinshijie.common.enums;

/**
 * 用户状态
 *
 * @author xinshijie
 */
public enum TypeEnums {
    SCIFI(6, "科学"),
    MAGIC(1, "武侠"),
    MARTIALARTS(2, "仙侠"),
    XIANXIA(3, "魔幻"),
    STRANGE(4, "奇幻"),
    OTHER(5, "其他");

    private final Integer code;
    private final String name;

    TypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (TypeEnums enable : values()) {
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
