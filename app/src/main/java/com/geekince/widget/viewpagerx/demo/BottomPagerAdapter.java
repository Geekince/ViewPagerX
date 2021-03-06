package com.geekince.widget.viewpagerx.demo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.geekince.widget.viewpagerx.dynamic.WrappingFragmentStatePagerAdapter;

import java.util.ArrayList;

public class BottomPagerAdapter extends WrappingFragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragmentList;

    public BottomPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i("ViewPagerX", "getItem " + position);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
