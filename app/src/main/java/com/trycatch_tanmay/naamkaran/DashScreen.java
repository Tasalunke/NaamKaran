package com.trycatch_tanmay.naamkaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashScreen extends AppCompatActivity {
    ImageView img_girl_logo,img_boy_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        img_girl_logo=findViewById(R.id.img_girl_logo);
        img_boy_logo=findViewById(R.id.img_boy_logo);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.Blue))));


        img_girl_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashScreen.this, GirlActivity.class);
                startActivity(intent);
            }
        });
        img_boy_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashScreen.this, BoysActivity.class);
                startActivity(intent);
            }
        });

    }
}