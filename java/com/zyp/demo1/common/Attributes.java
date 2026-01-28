package com.zyp.demo1.common;

import io.netty.util.AttributeKey;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Boolean> LOGIN2 = AttributeKey.newInstance("login2");
}
