package com.trycatch_tanmay.naamkaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class GirlActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    Button boybutton,wislistgirl;
    ImageView backButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl); // Assuming you have a corresponding XML layout file for girls
        getWindow().setStatusBarColor(getResources().getColor(R.color.Blue));

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backButton = findViewById(R.id.backButton);
        boybutton = findViewById(R.id.boybutton);
        wislistgirl=findViewById(R.id.wislistgirl);
        // Set up ViewPager with adapter
        GirlsPagerAdapter girlsPagerAdapter = new GirlsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(girlsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GirlActivity.this, DashScreen.class);
                startActivity(intent);
            }
        });
        wislistgirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GirlActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
        boybutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MediaPlayer mp = MediaPlayer.create(GirlActivity.this,R.raw.baby);
                mp.start();
                return false;
            }
        });
        boybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GirlActivity.this, BoysActivity.class);
                startActivity(intent);
            }
        });
    }

    private static class GirlsPagerAdapter extends FragmentPagerAdapter {

        private Hindu_girlfrag hinduGirlFragment;
        private Muslim_girlfrag muslimGirlFragment;
        private christian_girlfrag christianGirlFragment;

        public GirlsPagerAdapter(FragmentManager fm) {
            super(fm);
            hinduGirlFragment = new Hindu_girlfrag();
            muslimGirlFragment = new Muslim_girlfrag();
            christianGirlFragment = new christian_girlfrag();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return hinduGirlFragment;
                case 1:
                    return muslimGirlFragment;
                case 2:
                    return christianGirlFragment;
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