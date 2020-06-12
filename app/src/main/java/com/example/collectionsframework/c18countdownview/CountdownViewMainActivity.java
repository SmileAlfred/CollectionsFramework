package com.example.collectionsframework.c18countdownview;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collectionsframework.R;

import cn.iwgang.countdownview.CountdownView;

public class CountdownViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_view_main);

        TextView textView = (TextView) findViewById(R.id.tv_title);
        textView.setText("CountdownView秒杀倒计时");

        CountdownView mCvCountdownViewTest1 = (CountdownView)findViewById(R.id.cv_countdownViewTest1);
        mCvCountdownViewTest1.setTag("test1");
        //倒计5小时
        long time1 = (long)5 * 60 * 60 * 1000;
        mCvCountdownViewTest1.start(time1);

        CountdownView mCvCountdownViewTest2 = (CountdownView)findViewById(R.id.cv_countdownViewTest2);
        mCvCountdownViewTest2.setTag("test2");
        //倒计时30分
        long time2 = (long)30 * 60 * 1000;
        mCvCountdownViewTest2.start(time2);

        CountdownView cvCountdownViewTest211 = (CountdownView)findViewById(R.id.cv_countdownViewTest211);
        cvCountdownViewTest211.setTag("test21");
       //倒计时30分
        long time211 = (long)30 * 60 * 1000;
        cvCountdownViewTest211.start(time211);


        CountdownView mCvCountdownViewTest21 = (CountdownView)findViewById(R.id.cv_countdownViewTest21);
        mCvCountdownViewTest21.setTag("test21");
        //倒计时一天，24小时
        long time21 = (long)24 * 60 * 60 * 1000;
        mCvCountdownViewTest21.start(time21);

        CountdownView mCvCountdownViewTest22 = (CountdownView)findViewById(R.id.cv_countdownViewTest22);
        mCvCountdownViewTest22.setTag("test22");
        //倒计时30分钟
        long time22 = (long)30 * 60 * 1000;
        mCvCountdownViewTest22.start(time22);

        CountdownView mCvCountdownViewTest3 = (CountdownView)findViewById(R.id.cv_countdownViewTest3);
        //倒计时9小时
        long time3 = (long)9 * 60 * 60 * 1000;
        mCvCountdownViewTest3.start(time3);

        CountdownView mCvCountdownViewTest4 = (CountdownView)findViewById(R.id.cv_countdownViewTest4);
        //倒计时150天
        long time4 = (long)150 * 24 * 60 * 60 * 1000;
        mCvCountdownViewTest4.start(time4);

        final CountdownView mCvCountdownViewTest5 = (CountdownView)findViewById(R.id.cv_countdownViewTest5);
        new AsyncTask<Void, Long, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                long time = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        time += 1000;
                        publishProgress(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            protected void onProgressUpdate(Long... values) {
                super.onProgressUpdate(values);
                mCvCountdownViewTest5.updateShow(values[0]);
            }
        }.execute();

        CountdownView mCvCountdownViewTest6 = (CountdownView)findViewById(R.id.cv_countdownViewTest6);
        //倒计时两个小时
        long time6 = (long)2 * 60 * 60 * 1000;
        mCvCountdownViewTest6.start(time6);


        findViewById(R.id.btn_toDynamicShowActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountdownViewMainActivity.this, DynamicShowActivity.class));
            }
        });

        findViewById(R.id.btn_toListViewActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountdownViewMainActivity.this, CountdownViewListViewActivity.class));
            }
        });

        findViewById(R.id.btn_toRecyclerViewActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountdownViewMainActivity.this, CountdownViewRecyclerViewActivity.class));
            }
        });
    }

}
