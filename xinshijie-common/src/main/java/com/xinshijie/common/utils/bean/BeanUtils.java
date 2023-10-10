package com.xinshijie.common.utils.bean;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bean 工具类
 *
 * @author xinshijie
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * 拷贝指定源列表 到 指定目标bean类型，并返回目标bean列表
     *
     * @param targetClazz 目标bean 类型
     * @param sourceList  源bean 列表
     * @param <T>         指目标bean类型
     * @param <D>         指代源bean类型
     * @return 返回指定目标bean类型的列表
     */
    @Deprecated
    public static <T, D> List<T> copyForList(Class<T> targetClazz, List<D> sourceList) {
        if (Objects.isNull(sourceList) || Objects.isNull(targetClazz)) {
            return null;
        }
        return sourceList.stream().filter(Objects::nonNull).map(d -> {
            T t = null;
            try {
                //使用反射构建对象
                t = targetClazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (Objects.nonNull(t)) {
                BeanUtils.copyProperties(d, t);
            }
            return t;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 拷贝指定源列表 到 指定目标bean类型，并返回目标bean列表
     * List<UserDto> userDtos = BeanHelper.copyForList(UserDto::new, userDos);
     *
     * @param targetSupplier 目标bean对象提供者
     * @param sourceList     源bean 列表
     * @param <T>            指目标bean类型
     * @param <D>            指代源bean类型
     * @return 返回指定目标bean类型的列表
     */
    public static <T, D> List<T> copyForList(Supplier<T> targetSupplier, List<D> sourceList) {
        if (Objects.isNull(sourceList) || Objects.isNull(targetSupplier)) {
            return null;
        }
        return sourceList.stream().filter(Objects::nonNull).map(d ->
                        BeanUtils.copyForBean(targetSupplier, d))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 拷贝指定bean 到目标bean
     * 用法：
     * UserDto userDto=BeanHelper.copyForBean(UserDto::new, useDo);
     *
     * @param targetSupplier
     * @param d
     * @param <T>
     * @param <D>
     * @return
     */
    public static <T, D> T copyForBean(Supplier<T> targetSupplier, D d) {
        if (Objects.isNull(targetSupplier) || Objects.isNull(d)) {
            return null;
        }
        T t = null;
        t = targetSupplier.get();
        if (Objects.nonNull(t)) {
            BeanUtils.copyProperties(d, t);
        }
        return t;
    }

//    public static void main(String[] args) {
//        copyEntity();
//        //JDK1.7一下使用如下方法
//        List<UserVO> userVOListx =BeanHelper.copyForList(UserVO.class, copyEntity());
//        System.out.println(userVOListx);
//        System.out.println("============================================>>>");
//        //JDK1.8使用该方法
//        List<UserVO> userVOList =BeanHelper.copyForList(UserVO::new, copyEntity());
//        System.out.println(userVOList);
//    }
//
//    private static List<User> copyEntity() {
//        List<User> userList = new ArrayList<>();
//        User user1 = new User();
//        user1.setId(1);
//        user1.setName("zhangsan1");
//        user1.setPassword("123456");
//        user1.setPerms("system:test:add");
//
//        User user2 = new User();
//        user2.setId(2);
//        user2.setName("zhangsan2");
//        user2.setPassword("123456");
//        user2.setPerms("system:test:edit");
//
//        userList.add(user1);
//        userList.add(user2);
//        return userList;
//    }
}
