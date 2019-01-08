package com.la.server.attribute;

import com.la.server.session.Session;

import io.netty.util.AttributeKey;


public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
