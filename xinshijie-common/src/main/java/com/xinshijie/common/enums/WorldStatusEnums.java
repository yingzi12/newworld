package com.xinshijie.common.enums;

/**
 * 元素状态
 */
public enum WorldStatusEnums {
    NORMAL(5, "正常"),
    DRAFT(1, "草稿"),
    //只有管理员可以编辑
    LOCK(2, "锁定"),
    //只有管理员可见
    HIDE(3, "隐藏"),
    DEL(4, "删除");

    private final Integer code;
    private final String name;

    WorldStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (WorldStatusEnums enable : values()) {
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
