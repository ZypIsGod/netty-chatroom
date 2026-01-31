package com.zyp.demo1.common;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public interface Command {

    byte LOGIN_REQUEST = 1;
    byte LOGIN_RESPONSE = 2;
    byte MESSAGE_REQEUST = 3;
    byte MESSAGE_RESPONSE = 4;
    byte CREATE_GROUP_REQUEST = 5;
    byte CREATE_GROUP_RESPONSE = 6;
}
