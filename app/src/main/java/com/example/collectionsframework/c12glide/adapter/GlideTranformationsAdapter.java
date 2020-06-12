package com.example.collectionsframework.c12glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.collectionsframework.R;
import com.example.collectionsframework.c10picasso.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by Administrator on 2016/10/28.
 */

public class GlideTranformationsAdapter extends RecyclerView.Adapter<GlideTranformationsAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public GlideTranformationsAdapter(Context context) {
        mContext = context;
        for (int i = 1; i <= 11; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_glide_tranformations, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // 设置名称
        holder.name.setText("item" + (position + 1));

        int integer = Integer.parseInt(mData.get(position));

        switch (integer) {
            case 1: {
                int width = Utils.dip2px(mContext, 133.33f);
                int height = Utils.dip2px(mContext, 126.33f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .transform(new CenterCrop(), new MaskTransformation(R.drawable.mask_starfish))
                        .into(holder.image);
                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .transform(new CenterCrop(), new MaskTransformation(R.drawable.mask_chat_right))
                        .into(holder.image);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.CropType.TOP))
                        .into(holder.image);
                break;
            case 4:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100))
                        .into(holder.image);
                break;
            case 5:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.CropType.BOTTOM)).into(holder.image);

                break;
            case 6:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropSquareTransformation())
                        .into(holder.image);
                break;
            case 7:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new CropCircleTransformation())
                        .into(holder.image);
                break;
            case 8:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
                        .into(holder.image);
                break;
            case 9:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new GrayscaleTransformation())
                        .into(holder.image);
                break;
            case 10:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .transform(new RoundedCornersTransformation(30, 0, RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(holder.image);
                break;
            case 11:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .transform(new BlurTransformation(25))
                        .into(holder.image);
                break;
            /**
             * 以下，.transform()中的动画类出错；
             */
            //case 12:
            //   Glide.with(mContext)
            //           .load(R.drawable.demo)
            //           .transform(new ToonFilterTransformation(2.0f,10.f))
            //           .into(holder.image);
            //   break;
            // case 13:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new SepiaFilterTransformation())
            //             .into(holder.image);
            //     break;
            // case 14:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new ContrastFilterTransformation(2.0f))
            //             .into(holder.image);
            //     break;
            // case 15:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new InvertFilterTransformation())
            //             .into(holder.image);
            //     break;
            // case 16:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new PixelationFilterTransformation(20))
            //             .into(holder.image);
            //     break;
            // case 17:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new SketchFilterTransformation())
            //             .into(holder.image);
            //     break;
            // case 18:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f)))
            //             .into(holder.image);
            //     break;
            // case 19:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new BrightnessFilterTransformation(0.5f))
            //             .into(holder.image);
            //     break;
            // case 20:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new KuwaharaFilterTransformation(25))
            //             .into(holder.image);
            //     break;
            // case 21:
            //     Glide.with(mContext)
            //             .load(R.drawable.check)
            //             .transform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f), new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
            //             .into(holder.image);
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_glide_tranfromations)
        ImageView image;

        @BindView(R.id.tv_glide_name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
