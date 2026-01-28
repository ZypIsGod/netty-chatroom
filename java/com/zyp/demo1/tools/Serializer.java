package com.zyp.demo1.tools;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转换成二进制数据
     */
    byte[] serializer(Object object);

    /**
     * 二进制转java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
