package com.xinshijie.common.enums;

import lombok.Getter;

@Getter
public enum RankEnums {
    rank0(0,0,0,0,0),
    rank1(1,100,1000,100,100),
    rank2(2,300,3000,300,300),
    rank3(3,900,9000,900,900),
    rank4(4,2100,21000,2100,2100),
    rank5(5,4200,42000,4200,4200),
    rank6(6,6300,63000,6300,6300),
    rank7(7,10500,105000,10500,10500),
    rank8(8,16800,168000,16800,16800),
    rank9(9,37200,372000,37200,37200),
    rank10(10,999999999,9999999,99999999,9999999),
            ;

    private final Integer rank;
    //经验值 用户在网站的活动获得
    private final Integer exp;
    //生命力 用于提升世界的等级
    private final Integer vitality;
    //贡献值 用户提升用户在世界的等级
    private final Integer credit;
    //传播度 用于提示故事等级
    private final Integer diffuse;

     RankEnums(Integer rank, Integer exp, Integer vitality, Integer credit, Integer diffuse) {
        this.rank = rank;
        this.exp = exp;
        this.vitality = vitality;
        this.credit = credit;
        this.diffuse = diffuse;
    }

    public static RankEnums getRankEnums(Integer rank) {
        for (RankEnums rankEnums : values()) {
            if (rankEnums.getRank() == rank) {
                return rankEnums;
            }
        }
        return null;
    }
}
