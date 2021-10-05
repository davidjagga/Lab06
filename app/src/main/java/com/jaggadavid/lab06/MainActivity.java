package com.jaggadavid.lab06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    RecyclerView.Adapter fragmentSateAdapter;
    int NUM_ITEM=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.container);

        fragmentSateAdapter = new MyFragmentStateAdapter(this);

        viewPager2.setAdapter(fragmentSateAdapter);
    }

    private class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(viewPager2, position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEM;
        }
    }
}