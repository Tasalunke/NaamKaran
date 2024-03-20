package com.trycatch_tanmay.naamkaran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.white))));
        // Set the title and information text
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView titleTextView = findViewById(R.id.textViewTitle);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView infoTextView = findViewById(R.id.textViewInfo);

        // Customize the text if needed
        titleTextView.setText("NAAMKARAN - About Us");

        // Set your About Us information here
        String aboutUsInfo = " NAAMKARAN is an application that helps you find meaningful   names for your newborn baby. " +
                "We provide a wide range of baby    names categorized into Hindu, Muslim, and Christian names, " +
                " along with their meanings. Our mission is to assist parents in  finding the perfect name " +
                "that carries a beautiful significance for  their child's future.";

        infoTextView.setText(aboutUsInfo);
    }
}