package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum UserOptionEnums {
    ADD_ELEMENT(0, "添加元素", 8, 10, 10, 0, 0, 0, "ADD_ELEMENT"),
    ADD_WORLD(1, "添加世界", 10, 0, 12, 0, 0, 0, "ADD_WORLD"),
    ADD_COMMENT(2, "添加评论", 3, 1, 1, 0, 0, 0, "ADD_COMMENT"),
    ADD_DISCUSS(3, "添加讨论", 5, 1, 1, 0, 0, 0, "ADD_DISCUSS"),
    ADD_FOLLOW(4, "关注", 1, 0, 1, 0, 0, 0, "ADD_FOLLOW"),
    REPLY_COMMENT(5, "回复评论", 2, 1, 1, 0, 0, 0, "REPLY_COMMENT"),
    REPLY_DISCUSS(6, "回复讨论", 2, 1, 1, 0, 0, 0, "REPLY_DISCUSS"),
    EDIT_ELEMENT(7, "编辑元素", 3, 1, 2, 0, 0, 0, "EDIT_ELEMENT"),
    AUDIT_ELEMENT(8, "审核元素", 3, 3, 1, 0, 0, 0, "AUDIT_ELEMENT"),
    AUDIT_ELEMENT_YES(9, "元素审核通过", 3, 5, 3, 0, 0, 0, "AUDIT_ELEMENT_YES"),
    SEE_ELEMENT(10, "查看元素", 1, 0, 1, 0, 0, 0, "SEE_ELEMENT"),
    ADD_RESIDENT(11, "新居民", 0, 1, 0, 0, 0, 0, "ADD_RESIDENT"),
    SIGN(12, "签到", 1, 0, 0, 0, 0, 0, "SIGN"),
    WORLD_SIGN(13, "世界签到", 1, 1, 1, 0, 0, 0, "WORLD_SIGN"),
    DEL_WORLD(14, "删除元素", -8, -10, -10, 0, 0, 0, "DEL_WORLD"),
    DEL_ELEMENT(15, "删除元素", -8, -10, -10, 0, 0, 0, "DEL_ELEMENT"),
    DEL_COMMENT(16, "删除评论", -8, -10, -10, 0, 0, 0, "DEL_COMMENT"),
    DEL_DISCUSS(17, "删除讨论", -8, -10, -10, 0, 0, 0, "DEL_DISCUSS"),
    DEL_FOLLOW(18, "取消关注", -8, -10, -10, 0, 0, 0, "DEL_FOLLOW"),
    ADD_STORY(19, "添加故事", 8, 10, 10, 0, 0, 0, "ADD_STORY"),
    DEL_STORY(20, "删除故事", -8, -10, 10, 0, 0, 0, "DEL_STORY"),
    ADD_HARDING(21, "添加收藏", -1, -1, -1, -1, 0, 0, "ADD_HARDING"),
    DEL_HARDING(22, "删除收藏", -1, -1, -1, -1, 0, 0, "DEL_HARDING"),
    SEE_STORY(23, "查看故事", 1, 1, 10, 0, 0, 0, "SEE_STORY"),
    ADD_CHAPTER(24, "添加章节", 5, 10, 10, 0, 0, 0, "ADD_CHAPTER"),
    EDIT_CHAPTER(25, "编辑章节", 5, 10, 10, 0, 0, 0, "EDIT_CHAPTER"),
    DEL_CHAPTER(26, "删除章节", -5, 10, 10, 0, 0, 0, "DEL_CHAPTER"),
    ADD_AUTHOR(27, "添加作者", -5, 10, 10, 0, 0, 0, "ADD_AUTHOR"),
    STORY_SIGINT(28, "故事签到", 8, 10, 10, 0, 0, 0, "STORY_SIGINT"),
    SEE_WORLD(29, "查看世界", 8, 10, 10, 0, 0, 0, "SEE_WORLD"),
    DEL_AUTHOR(30, "删除作者", -5, 10, 10, 0, 0, 0, "DEL_AUTHOR"),

    ;
    private final Integer code;
    private final String name;
    //经验值 用户在网站的活动获得
    private final Integer exp;
    //生命力 用于提升世界的等级
    private final Integer vitality;
    //贡献值 用户提升用户在世界的等级
    private final Integer credit;
    //传播度
    private final Integer diffuse;
    //最大次数
    private final Integer max;
    //每天最大次数 0表示不限制
    private final Integer maxDay;
    //redis key
    private final String key;

    UserOptionEnums(Integer code, String name, Integer exp, Integer vitality, Integer credit, Integer diffuse, Integer max, Integer maxDay, String key) {
        this.code = code;
        this.name = name;
        this.exp = exp;
        this.vitality = vitality;
        this.credit = credit;
        this.diffuse = diffuse;
        this.max = max;
        this.maxDay = maxDay;
        this.key = key;
    }

    public static String getNameByCode(Integer code) {
        for (UserOptionEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable.getName();
            }
        }
        return "";
    }

    public static UserOptionEnums getEnumsByCode(Integer code) {
        for (UserOptionEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserOptionEnums{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", exp=" + exp +
                ", vitality=" + vitality +
                ", credit=" + credit +
                ", diffuse=" + diffuse +
                ", max=" + max +
                ", maxDay=" + maxDay +
                ", key='" + key + '\'' +
                '}';
    }
}
