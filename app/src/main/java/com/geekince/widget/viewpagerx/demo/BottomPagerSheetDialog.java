package com.geekince.widget.viewpagerx.demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.geekince.widget.viewpagerx.bottomsheet.ViewPagerBottomSheetDialog;
import com.geekince.widget.viewpagerx.bottomsheet.ViewPagerBottomSheetDialogFragment;
import com.geekince.widget.viewpagerx.dynamic.WrappingViewPager;

import java.util.ArrayList;

public class BottomPagerSheetDialog extends ViewPagerBottomSheetDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(ViewPagerBottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewPagerBottomSheetDialog dialog = (ViewPagerBottomSheetDialog) getDialog();
        if (dialog != null) {
            dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_bottom_dialog;
    }

    @Override
    protected void initView() {
        super.initView();
        WrappingViewPager viewPager = mRootView.findViewById(R.id.view_pager_x);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(BottomListFragment.newInstance(6));

        BottomListFragment listFragment = BottomListFragment.newInstance(20);
        fragmentList.add(listFragment);

        BottomPagerAdapter bottomPagerAdapter = new BottomPagerAdapter(
                getChildFragmentManager(),
                fragmentList);

        listFragment.setOnGlobalLayoutListener(new BottomListFragment.OnGlobalLayoutListener() {
            @Override
            public void returnPadding(int padding) {
                bottomPagerAdapter.setPadding(padding);
            }
        });

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(bottomPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                onPageChange(viewPager);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

}
