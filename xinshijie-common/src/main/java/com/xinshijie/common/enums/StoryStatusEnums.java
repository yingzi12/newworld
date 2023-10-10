package com.xinshijie.common.enums;

/**
 * 元素状态
 */
public enum StoryStatusEnums {
    DRAFT(1, "草稿"),
    ISSUE(2, "发布"),
    NORMAL(3, "审核通过正常"),
    DEL(4, "删除"),
    AUDITNO(5, "审核不通过"),
    //只有管理员可见
    HIDE(6, "隐藏"),
    //只有管理员可以编辑
    LOCK(7, "锁定"),
    ;
    private final Integer code;
    private final String name;

    StoryStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (StoryStatusEnums enable : values()) {
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
