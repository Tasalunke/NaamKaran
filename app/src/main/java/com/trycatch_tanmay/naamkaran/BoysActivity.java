package com.trycatch_tanmay.naamkaran;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class BoysActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    ImageView backButton;
    Button girlbutton,wislistboy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backButton = findViewById(R.id.backButton);
        girlbutton=findViewById(R.id.girlbutton);
        wislistboy=findViewById(R.id.wislistboy);
        getWindow().setStatusBarColor(getResources().getColor(R.color.Yellow));

        // Set up ViewPager with adapter
        BoysPagerAdapter boysPagerAdapter = new BoysPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(boysPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoysActivity.this, DashScreen.class);
                startActivity(intent);
            }
        });

        girlbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MediaPlayer mp = MediaPlayer.create(BoysActivity.this,R.raw.baby);
                mp.start();
                return false;
            }
        });
        girlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoysActivity.this, GirlActivity.class);
                startActivity(intent);
            }
        });
        wislistboy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoysActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
    }


    private static class BoysPagerAdapter extends FragmentPagerAdapter {

        private HinduFragment hinduFragment;
        private MuslimFragment muslimFragment;
        private ChristianFragment christianFragment;

        public BoysPagerAdapter(FragmentManager fm) {
            super(fm);
            hinduFragment = new HinduFragment();
            muslimFragment = new MuslimFragment();
            christianFragment = new ChristianFragment();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return hinduFragment;
                case 1:
                    return muslimFragment;
                case 2:
                    return christianFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // Number of tabs
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HINDU";
                case 1:
                    return "MUSLIM";
                case 2:
                    return "CHRISTIAN";
                default:
                    return null;
            }
        }
    }
}
