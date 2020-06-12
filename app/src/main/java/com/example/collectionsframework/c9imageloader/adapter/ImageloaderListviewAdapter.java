package com.example.collectionsframework.c9imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c9imageloader.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ImageloaderListviewAdapter extends BaseAdapter {
    private Context mContext;
    private final ImageLoader imageLoader;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.drawable.i_icon)          // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.drawable.i_icon)  // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.i_icon)       // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
            .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
            .build();                                   // 创建配置过得DisplayImageOption对象;

    public ImageloaderListviewAdapter(Context context) {
        mContext = context;

        // 初始化imageloader
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
    }

    @Override
    public Object getItem(int position) {
        return Constants.IMAGES[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取或创建viewhoder
        Viewholder holder;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_imageloader_listview, null);

            holder = new Viewholder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }

        // 获取当前item数据

        // 显示数据
        holder.name.setText("item" + (position + 1));

        imageLoader.displayImage(Constants.IMAGES[position], holder.iv, options);

        return convertView;
    }

    class Viewholder {
        @BindView(R.id.iv_imageloader_listview)
        ImageView iv;

        @BindView(R.id.tv_imageloader_name)
        TextView name;

        public Viewholder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
