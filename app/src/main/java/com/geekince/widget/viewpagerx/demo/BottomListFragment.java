package com.geekince.widget.viewpagerx.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomListFragment extends Fragment {

    private OnGlobalLayoutListener mOnGlobalLayoutListener;

    public static BottomListFragment newInstance(int count) {
        BottomListFragment fragment = new BottomListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        int count = 6;
        if (bundle != null) {
            count = bundle.getInt("count");
        }

        View holderView = view.findViewById(R.id.holder);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new BottomListAdapter(count));

        if (count > 6) {
            holderView.setVisibility(View.VISIBLE);
            holderView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            holderView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            recyclerView.setPadding(0, holderView.getHeight(), 0, 0);
                            if (mOnGlobalLayoutListener != null) {
                                mOnGlobalLayoutListener.returnPadding(holderView.getHeight());
                            }
                        }
                    });
        }
    }

    public void setOnGlobalLayoutListener(OnGlobalLayoutListener listener) {
        this.mOnGlobalLayoutListener = listener;
    }

    public interface OnGlobalLayoutListener {
        void returnPadding(int padding);
    }

}
