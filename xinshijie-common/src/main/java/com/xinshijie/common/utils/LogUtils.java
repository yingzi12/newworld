package com.xinshijie.common.utils;

/**
 * 处理并记录日志文件
 *
 * @author xinshijie
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg + "]";
    }
}
