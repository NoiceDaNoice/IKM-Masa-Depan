package com.example.ikmmasadepanapp.ui.main;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ikmmasadepanapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("IKM Masa Depan");
        showFragment();
    }


    public void showFragment(){
        FragmentAdapter fragmentAdapter =  new FragmentAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(fragmentAdapter);

        @StringRes
        final int[] TAB_TITLES = new int[]{
                R.string.tab1,
                R.string.tab2,

        };
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}