package com.example.administrator.custombanner.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.custombanner.R;

import java.util.List;

public class LJNCBPageAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private LJNCBViewHolderCreator holderCreator;
    private boolean canLoop = true;
    private LJNCBLoopViewPager viewPager;
    private static final int MULTIPLE_COUNT = 300;

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        return position % realCount;
    }

    @Override
    public int getCount() {
        return canLoop ? getRealCount() * MULTIPLE_COUNT : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = toRealPosition(position);
        View view = getView(realPosition, null, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = viewPager.getFirstItem();
        } else if (position == getCount() - 1) {
            position = viewPager.getLastItem();
        }
        try {
            viewPager.setCurrentItem(position, false);
        } catch (IllegalStateException ignored) {
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void setViewPager(LJNCBLoopViewPager viewPager) {
        this.viewPager = viewPager;
    }

    LJNCBPageAdapter(LJNCBViewHolderCreator holderCreator, List<T> datas) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
    }

    @SuppressWarnings({"unchecked", "SameParameterValue"})
    private View getView(int position, View view, ViewGroup container) {
        LJNHolder holder;
        if (view == null) {
            holder = (LJNHolder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.cb_tag, holder);
        } else {
            holder = (LJNHolder<T>) view.getTag(R.id.cb_tag);
        }
        if (mDatas != null && !mDatas.isEmpty()) {
            holder.updateUI(container.getContext(), position, mDatas.get(position));
        }
        return view;
    }

}
