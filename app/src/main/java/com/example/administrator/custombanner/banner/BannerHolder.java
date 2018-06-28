package com.example.administrator.custombanner.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @description: 绑定图片
 * @author: ljn
 * @time: 2018/6/27
 */
public class BannerHolder implements LJNHolder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void updateUI(Context context, int position, String data) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CornersTransform(context, 5))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(data)
                .apply(requestOptions)
                .into(imageView);
    }
}
