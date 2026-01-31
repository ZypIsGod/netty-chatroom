package com.zyp.demo1.tools;

import com.zyp.demo1.common.Attributes;
import com.zyp.demo1.pojo.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class SessionUtil {

    private static Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    public static Channel getChannel(String userId) {
        return userChannelMap.get(userId);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static void bindSession(Session session, Channel channel) {
        channel.attr(Attributes.SESSION).set(session);
        userChannelMap.put(session.getUserId(), channel);
    }

    public static void removeChannel(String userId) {
        userChannelMap.remove(userId);
    }
}
