package com.geekince.widget.viewpagerx.dynamic;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * FragmentStatePagerAdapter that automatically works with WrappingViewPager, if you don't want to
 * implement the necessary logic in the adapter yourself.
 * <p>
 * Make sure you only use this adapter with a {@link WrappingViewPager}, otherwise your app
 * will crash!
 *
 * @author Santeri Elo
 */
public abstract class WrappingFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private int mCurrentPosition = -1;
    private int mPadding;

    public WrappingFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param container View container (instanceof {@link WrappingViewPager}))
     * @param position  Item position
     * @param object    {@link Fragment}
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        if (!(container instanceof WrappingViewPager)) {
            throw new UnsupportedOperationException("ViewPager is not a WrappingViewPager");
        }

        Fragment fragment = (Fragment) object;
        WrappingViewPager pager = (WrappingViewPager) container;
        if (fragment != null && fragment.getView() != null) {
            if (position != mCurrentPosition) {
                mCurrentPosition = position;
            }
            pager.onPageChanged(fragment.getView(), mPadding);
        }
    }

    public void setPadding(int padding) {
        this.mPadding = padding;
    }

}