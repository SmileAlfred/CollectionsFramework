package com.example.collectionsframework.c16jiecaovideoplayer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c16jiecaovideoplayer.utils.VideoConstant;
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.MyViewHolder> {

    int[] videoIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Context context;
    public static final String TAG = RecyclerViewVideoAdapter.class.getSimpleName();

    public RecyclerViewVideoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_videoview, parent,
                false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder [" + holder.jcVideoPlayer.hashCode() + "] position=" + position);

        //设置播放地址和标题
        holder.jcVideoPlayer.setUp(
                VideoConstant.videoUrls[0][position], JCVideoPlayer.SCREEN_LAYOUT_LIST,
                VideoConstant.videoTitles[0][position]);

        //设置封面
        Picasso.get()
                .load(VideoConstant.videoThumbs[0][position])
                .into(holder.jcVideoPlayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return videoIndexs.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }
}
