package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum SofttypeEnums {
    STANDARD(1,"标准"),
    FORCES(2,"势力"),
    GEOGRAPHY(3,"地理"),
    ORGANISM(4,"生物"),
    RACE(5,"种族"),
    ROLE(6,"角色"),
    THING(7,"物品/材料"),
    ;

    private Integer code;

    private String name;

    SofttypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SofttypeEnums getByCode(Integer code) {
        for (SofttypeEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable;
            }
        }
        return null;
    }
}
