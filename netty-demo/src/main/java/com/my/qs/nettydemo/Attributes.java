package com.my.qs.nettydemo;

import com.my.qs.nettydemo.pojo.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
