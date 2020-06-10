package com.example.collectionsframework.c10picasso;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/15.
 */
public class Utils {
    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
