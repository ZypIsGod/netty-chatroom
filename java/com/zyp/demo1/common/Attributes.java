package com.zyp.demo1.common;

import com.zyp.demo1.pojo.Session;
import io.netty.util.AttributeKey;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
    AttributeKey<Boolean> LOGIN2 = AttributeKey.newInstance("login2");
    AttributeKey<String> USER_ID = AttributeKey.newInstance("user_id");


}
