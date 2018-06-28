package com.example.administrator.custombanner.banner;

import android.content.Context;
import android.view.View;

public interface LJNHolder<T> {

    View createView(Context context);
    void updateUI(Context context, int position, T data);
}
