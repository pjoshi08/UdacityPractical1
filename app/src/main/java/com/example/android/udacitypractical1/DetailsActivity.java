package com.example.android.udacitypractical1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView mUsername, mEmail, mBio;
    private ImageView mImageView;
    private static final String My_PREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mUsername = findViewById(R.id.demo_username);
        mEmail = findViewById(R.id.demo_email);
        mBio = findViewById(R.id.demo_bio);
        mImageView = findViewById(R.id.profile_image);

        SharedPreferences sharedPreferences = getSharedPreferences(My_PREFERENCES, Context.MODE_PRIVATE);

        mUsername.setText(sharedPreferences.getString("username", getResources().getString(R.string.demo_username)));
        mEmail.setText(sharedPreferences.getString("email", getResources().getString(R.string.demo_email)));
        mBio.setText(sharedPreferences.getString("bio", getResources().getString(R.string.demo_bio)));
        mImageView.setImageResource(sharedPreferences.getInt("gender_image", R.drawable.boy));
    }
}
