package com.example.collectionsframework.c20tablayout.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c20tablayout.adapter.ViewPagerAdapter;
import com.example.collectionsframework.c20tablayout.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabLayoutMainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TextView tv_title;
    TabLayout tabLayout;

    ArrayList<MyFragment> fragments;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tv_title.setText("TabLayout的使用");

        //初始化数据
        fragments = new ArrayList<>();
        for (int i=0;i<12;i++){
            fragments.add(new MyFragment("标题"+i,"内容"+i));
        }
        //设置ViewPager的适配器
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        tabLayout.setupWithViewPager(viewPager);
        //设置固定的
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

}
