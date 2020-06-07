package com.example.collectionsframework.c7eventbus.event;

/**
 * Created by Administrator on 2016/10/13.
 */
// 1创建一个粘性事件类
public class StickyEvent {
    public String msg;

    public StickyEvent(String msg) {
        this.msg = msg;
    }
}
