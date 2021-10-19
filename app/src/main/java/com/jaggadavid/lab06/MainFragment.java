package com.jaggadavid.lab06;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;

public class MainFragment extends Fragment {
    ViewPager2 viewPager2;


    int position;
    int pointer = 0;

    public static Fragment newInstance(ViewPager2 viewPager2, int pos) {

        MainFragment fragment = new MainFragment();
        fragment.viewPager2 = viewPager2;
        fragment.position = pos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText("I am  " + (position + 1))
        ).attach();
        Button mButton  = view.findViewById(R.id.pressme);
        playQuote(mButton);

    }

    @SuppressLint("SetTextI18n")
    public void playQuote(View view) {
        TypedArray arrayOfQuotes = getResources().obtainTypedArray(R.array.quotes);
        //Log.i("Button", (arrayOfQuotes.getResourceId(10, -1)+"")+"got click");
        //mediaPlayer.start();

        int random_int = (int)Math.floor(Math.random()*(arrayOfQuotes.length()));
        ((Button) view).setText(arrayOfQuotes.getString(random_int)+"");

    }

}

