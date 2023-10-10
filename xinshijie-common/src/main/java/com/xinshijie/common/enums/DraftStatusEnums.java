package com.xinshijie.common.enums;

/**
 * 审核状态
 */
public enum DraftStatusEnums {
    //不能发布,可编辑
    DRAFT(7, "草稿"),
    //可以发布,可以编辑
    SAVE(7, "保存待发布"),
    //不能编辑,等待审核
    ISSUE(1, "发布"),
    DELETE(4, "删除"),
    AUDITYES(2, "通过审核"),
    AUDITNO(3, "审核不通过"),
    AUTORISSOR(5, "超时发布自动拒绝"),
    AUTORAUDIT(6, "超时审核自动通过"),
    ;
    private final Integer code;
    private final String name;

    DraftStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (DraftStatusEnums enable : values()) {
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
