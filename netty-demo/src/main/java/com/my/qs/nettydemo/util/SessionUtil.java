package com.my.qs.nettydemo.util;

import com.my.qs.nettydemo.Attributes;
import com.my.qs.nettydemo.pojo.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private static final ConcurrentHashMap<String, Channel> sessionMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        sessionMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            sessionMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return sessionMap.get(userId);
    }

    public static void storeGroup(String groupId, ChannelGroup channelGroup){
        groupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getGroup(String groupId){
        return groupMap.get(groupId);
    }
}
