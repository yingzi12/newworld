package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum RecommendEnums {
    HEAD(1,"HEAD头的轮播图",6),
    EDIT(2,"编辑推荐",6),
    POTENTAL(3,"最有潜力得",9),
    EXCELLENT(4,"最优秀得",12),
    NEWS(5,"最新的",12),
    OTHER(6,"非原创推荐",12),
    ORIGIANL(7,"原创推荐",12),
    RONDOM(8,"随机推荐",4),
    ACTIVE_DAY(9,"今日最活跃",4),
    ACTIVE_WEEK(10,"本周最活跃",4),
    ACTIVE_MONTHLY(11,"本月最活跃",12),
    RECOMMED_DAY(12,"今日推荐",12),
    RECOMMED_WEEK(13,"本周推荐",12),
    RECOMMED_MONTHLY(14,"本月推荐",4),
    RANK(15,"等级最高的",4),
    RESIDENT(16,"居民最多的",4),
    BOUTIQUE(17,"精品",9),
    ;
    private Integer code;
    private String describe;
    private Integer max;

    RecommendEnums(Integer code, String describe,Integer max) {
        this.code = code;
        this.describe = describe;
        this.max = max;
    }
    public static RecommendEnums getByCode(Integer code) {
        for (RecommendEnums enable : values()) {
            if (enable.getCode() == code) {
                return enable;
            }
        }
        return null;
    }
}
