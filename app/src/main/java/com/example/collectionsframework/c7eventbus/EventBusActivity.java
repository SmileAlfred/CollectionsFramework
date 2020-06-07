package com.example.collectionsframework.c7eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c7eventbus.event.MessageEvent;
import com.example.collectionsframework.c7eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus;问题：对使用情景不了解，暂时做不到灵活使用；
 * 步骤：
 * 1. 注册广播：EventBus.getDefault().register(EventBusActivity.this);
 * 2. 解注册：EventBus.getDefault().unregister(EventBusActivity.this);
 * 3. 创建发送消息类；
 * 4. 发送消息：EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据"));
 * 5. 接收消息;@Subscribe(threadMode = ThreadMode.MAIN)
 *     public void MesssageEventBus(MessageEvent event) {
 * 注意：粘性事件的顺序：3 - 4 - 5 - 1 - 2
 *
 */
public class EventBusActivity extends Activity {
    private TextView tv_title;
    private Button bt_eventbus_send;
    private Button bt_eventbus_sticky;
    private TextView tv_eventbus_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_bus);

        initView();

        initData();

        initListener();
    }

    private void initData() {
        tv_title.setText("EventBus");

        // 1注册广播
        EventBus.getDefault().register(EventBusActivity.this);
    }

    private void initListener() {
        // 跳转到发送页面
        bt_eventbus_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EventBusActivity.this, EventBusSendActivity.class);

                startActivity(intent);
            }
        });

        // 发送粘性事件到发送页面
        bt_eventbus_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2 发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("我是粘性事件"));

                // 跳转到发送数据页面
                Intent intent = new Intent(EventBusActivity.this, EventBusSendActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);

        bt_eventbus_send = (Button) findViewById(R.id.bt_eventbus_send);
        bt_eventbus_sticky = (Button) findViewById(R.id.bt_eventbus_sticky);
        tv_eventbus_result = (TextView) findViewById(R.id.tv_eventbus_result);
    }


    // 5 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MesssageEventBus(MessageEvent event) {

        // 显示接收的消息
        tv_eventbus_result.setText(event.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 2 解注册
        EventBus.getDefault().unregister(EventBusActivity.this);
    }
}
