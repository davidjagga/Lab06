package com.jaggadavid.lab06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ViewPager2 viewPager2;
    ArrayList<Integer> soundArray = new ArrayList<>();
    RecyclerView.Adapter fragmentSateAdapter;
    TypedArray arrayOfQuotes;
    int quoteNum = 0;
    int NUM_ITEM=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypedArray arrayOfQuotes = getResources().obtainTypedArray(R.array.quotes);
        Log.i("Button", (arrayOfQuotes.getResourceId(10, -1)+"")+"got click");

        quoteNum = 0;
        soundArray.addAll(Arrays.asList(R.raw.scream, R.raw.whoosh, R.raw.chewbaka, R.raw.next, R.raw.flip, R.raw.r2d2, R.raw.swipe));

        int random_int = (int)Math.floor(Math.random()*(soundArray.size()));
        mediaPlayer = MediaPlayer.create(getApplicationContext(), soundArray.get(random_int));
        mediaPlayer.setVolume(1,1);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.container);

        fragmentSateAdapter = new MyFragmentStateAdapter(this);

        viewPager2.setAdapter(fragmentSateAdapter);

        viewPager2.setPageTransformer(new DepthPageTransformer());

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }
        });
    }
    public void playQuote(View view) {
        TypedArray arrayOfQuotes = getResources().obtainTypedArray(R.array.quotes);
        Log.i("Button", (arrayOfQuotes.getResourceId(10, -1)+"")+"got click");
        //mediaPlayer.start();

        ((Button) view).setText(arrayOfQuotes.getResourceId(quoteNum, -1)+"");
        quoteNum = (quoteNum == 103) ? 0 : quoteNum+1;
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

    private class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(@NonNull View page, float position) {

            Log.i("soundPlayed", "hello");
            int random_int = (int)Math.floor(Math.random()*(soundArray.size()));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), soundArray.get(random_int));
            mediaPlayer.start();

            page.setTranslationX(-position * page.getWidth());
            page.setCameraDistance(12000);

            if (position < 0.5 && position > -0.5) {
                page.setVisibility(View.VISIBLE);
            } else {
                page.setVisibility(View.INVISIBLE);
            }


            if (position < -1) {     // [-Infinity,-1)
                page.setAlpha(0);

            } else if (position <= 0) {    // [-1,0]
                page.setAlpha(1);
                page.setRotationY(180 * (1 - Math.abs(position) + 1));

            } else if (position <= 1) {    // (0,1]
                page.setAlpha(1);
                page.setRotationY(-180 * (1 - Math.abs(position) + 1));

            } else {
                page.setAlpha(0);
            }
        }
    }
}