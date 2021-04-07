package com.geekince.widget.viewpagerx.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_btn).setOnClickListener(v -> {
            BottomPagerSheetDialog dialog = new BottomPagerSheetDialog();
            dialog.show(getSupportFragmentManager(),"BottomPagerSheetDialog");
        });
    }

}