package com.example.ikmmasadepanapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ikmmasadepanapp.ui.main.fragment.AddFragment;
import com.example.ikmmasadepanapp.ui.main.fragment.MainFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(AppCompatActivity activity) {
        super(activity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new AddFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
