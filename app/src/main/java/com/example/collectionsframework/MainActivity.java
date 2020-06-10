package com.example.collectionsframework;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.collectionsframework.c1fragment.CommonFrameFragment;
import com.example.collectionsframework.c2okhttp.base.BaseFragment;
import com.example.collectionsframework.c1fragment.CustomFragment;
import com.example.collectionsframework.c1fragment.OtherFragment;
import com.example.collectionsframework.c1fragment.ThirdPartyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：尚硅谷-杨光福 on 2016/7/21 18:42
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：主页面
 */
public class MainActivity extends FragmentActivity {

    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架；要写在监听之后，否则打开没有数据；
        mRg_main.check(R.id.rb_common_frame);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                //第三方
                case R.id.rb_thirdparty:
                    position = 1;
                    break;
                //自定义
                case R.id.rb_custom:
                    position = 2;
                    break;
                //其他
                case R.id.rb_other:
                    position = 3;
                    break;
                //常用框架，默认选他
                case R.id.rb_common_frame:
                default:
                    position = 0;
                    break;
            }

            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent, to);
        }
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }

    /**
     * 存在的安全隐患，使用 replace 切换页面会造成页面数据的反复加载；应该优化
     */
//    private void switchFrament(BaseFragment fragment) {
//        //1.得到FragmentManger
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content, fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    private BaseFragment getFragment() {
        //根据位置得到对应的Fragment;
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        //常用框架Fragment
        //mBaseFragment.add(new CommonFrameFragment());
        mBaseFragment.add(new CommonFrameFragment());
        //第三方Fragment
        mBaseFragment.add(new ThirdPartyFragment());
        //自定义控件Fragment
        mBaseFragment.add(new CustomFragment());
        //其他Fragment
        mBaseFragment.add(new OtherFragment());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}
