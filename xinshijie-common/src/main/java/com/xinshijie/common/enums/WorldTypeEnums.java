package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum WorldTypeEnums {
             t1(1,"武侠"),
             t2(2,"仙侠"),
             t3(3,"魔幻"),
             t4(4,"奇幻"),
    t5(5,"其他"),
    t6(6,"科学"),
    t7(7,"玄幻"),

    ;
      private Integer code;
      private String name;

    WorldTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (WorldTypeEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable.getName();
            }
        }
        return "";
    }
}
