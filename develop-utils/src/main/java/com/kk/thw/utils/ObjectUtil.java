package com.kk.thw.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * 对象工具类(赋值)
 *
 * @author tianhuiwen
 * @date 2018-11-05
 */
public class ObjectUtil {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据基本类型，给对象赋值
     *
     * @param c           对象
     * @param time        时间
     * @param resouceType 资源类型
     * @param resourceId  id
     * @param <T>         泛型
     * @return 对象
     * @throws Exception 异常
     */
    private <T> T getBean(Class<T> c, Long time, String resouceType, String resourceId) throws Exception {
        T t = c.newInstance();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            // 获取具体值
            field.setAccessible(true);
            // 其余情况根据类型赋值
            String fieldClassName = field.getType().getSimpleName();
            Random rand = new Random();
            if (fieldClassName.equalsIgnoreCase("String")) {
                if (fieldName.equals("resourceId")) {
                    field.set(t, resourceId);
                } else {
                    field.set(t, resouceType);
                }
            } else if (fieldClassName.equalsIgnoreCase("int") || fieldClassName.equals("Integer")) {
                int value = 100 + rand.nextInt(100);
                field.set(t, value);
                logger.info("指标名{}值{}", fieldName, value);
            } else if (fieldClassName.equalsIgnoreCase("double")) {
                double value = 200 + rand.nextInt(100);
                field.set(t, value);
                logger.info("指标名{}值{}", fieldName, value);
            } else if (fieldClassName.equalsIgnoreCase("long")) {
                field.set(t, time);
            } else {
                System.out.println("不支持的数据类型：" + fieldName + " : " + fieldClassName);
            }
        }
        return t;
    }
}
