package com.my.qs.nettydemo.protocol;

public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;
    Byte QuitGroupRequest = 7;
    Byte QuitGroupResponse = 8;
    Byte GroupMessageRequest = 9;
    Byte GroupMessageResponse = 10;
}
