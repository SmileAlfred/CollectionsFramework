package com.example.collectionsframework.c10picasso;

import android.content.Context;

public class Utils {
    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
