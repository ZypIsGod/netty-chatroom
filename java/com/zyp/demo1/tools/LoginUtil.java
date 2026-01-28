package com.zyp.demo1.tools;


import com.zyp.demo1.common.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);

        Boolean b = channel.attr(Attributes.LOGIN).get();
        System.out.println(b);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
