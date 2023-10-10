package com.xinshijie.common.utils;


import org.apache.commons.compress.utils.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <h3>agricultural</h3>
 * <p>bean字段拷贝工具类</p>
 **/
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <R, T> T copyProperties(R r, Supplier<T> supplier) {
        T t = supplier.get();
        copyProperties(r, t);
        return t;
    }

//    public static <R, T> Page<T> copyPage(Page<R> rPage, Supplier<T> supplier) {
//        return copyPage(rPage, supplier, null);
//    }
//
//    public static <R, T> Page<T> copyPage(Page<R> rPage, Supplier<T> supplier, BiConsumer<R, T> biConsumer) {
//        Page<T> page = new Page
//        page.setTotal(rPage.getTotal());
//        page.setCurrent(rPage.getCurrent());
//        page.setSize(rPage.getSize());
//        page.setPages(rPage.getPages());
//
//        List<T> list = copyList(rPage.getRecords(), supplier, biConsumer);
//        page.setRecords(list);
//        return page;
//    }

    public static <R, T> List<T> copyList(List<R> rList, Supplier<T> supplier) {
        return copyList(rList, supplier, null);
    }

    public static <R, T> List<T> copyList(List<R> rList, Supplier<T> supplier, BiConsumer<R, T> biConsumer) {
        if (CollectionUtils.isEmpty(rList)) {
            return Lists.newArrayList();
        }

        return rList.stream().map(r -> {
            T t = supplier.get();
            copyProperties(r, t);
            if (biConsumer != null) {
                biConsumer.accept(r, t);
            }
            return t;
        }).collect(Collectors.toList());
    }
}
