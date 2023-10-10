package com.xinshijie.common.utils;

public class ExpCountUtils {

    public static int[] getExp(int rank) {
        int value = 100;
        //修正
        int jc = 100;
        //基数
        float js = 1.431f;
        int[] vArr = new int[rank + 1];
        vArr[0] = 0;
        for (int i = 0; i <= rank; i++) {
            value = new Float(value + value * js + (jc * (i - 1))).intValue();
            vArr[i] = value;
        }
        return vArr;
    }
}
