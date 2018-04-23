package com.example.android.udacitypractical1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String MyPREFERENCES = "MyPrefs";
    private EditText mUsername;
    private EditText mEmail;
    private EditText mBio;
    private Intent mIntent;
    private android.support.v7.widget.Toolbar mToolbar;
    private ToggleButton mToggleButton;
    private ImageView mImageView;
    private String username, email, bio;
    private int image_id;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mBio = findViewById(R.id.bio);
        mToggleButton = findViewById(R.id.gender_toggle);
        mImageView = findViewById(R.id.profile_image);

        if(savedInstanceState == null) mImageView.setTag(R.drawable.boy);
        else mImageView.setTag(savedInstanceState.getInt("gender_image"));

        mIntent = new Intent(this, DetailsActivity.class);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void setSharedPreferences(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    private void setSharedPreferences(String key, int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private String getSharedPreference(String key){
        return sharedPreferences.getString(key, "");
    }

    private int getSharedPreference2(String key){
        return sharedPreferences.getInt(key, R.drawable.boy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.profile){
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void detailView(View view){
        setSharedPreferences("username", mUsername.getText().toString());
        setSharedPreferences("email", mEmail.getText().toString());
        setSharedPreferences("bio", mBio.getText().toString());
        setSharedPreferences("gender_image", getDrawableId(mImageView));

        mUsername.setText("");
        mEmail.setText("");
        mBio.setText("");

        startActivity(mIntent);
    }

    public void genderToggle(View view){
        if(mToggleButton.getText() == getResources().getString(R.string.gender_male)){
            mImageView.setImageResource(R.drawable.boy);
            mImageView.setTag(R.drawable.boy);
            setSharedPreferences("gender_id", R.drawable.boy);
        } else if(mToggleButton.getText() == getResources().getString(R.string.gender_female)) {
            mImageView.setImageResource(R.drawable.girl);
            mImageView.setTag(R.drawable.girl);
            mIntent.putExtra("gender_id", R.drawable.girl);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("username", mUsername.getText().toString());
        outState.putString("email", mEmail.getText().toString());
        outState.putString("bio", mBio.getText().toString());
        outState.putInt("gender_image", getDrawableId(mImageView));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        username = savedInstanceState.getString("username");
        mUsername.setText(username);

        email = savedInstanceState.getString("email");
        mEmail.setText(email);

        bio = savedInstanceState.getString("bio");
        mBio.setText(bio);

        image_id = savedInstanceState.getInt("gender_image");
        mImageView.setImageResource(image_id);
    }

    private int getDrawableId(ImageView iv){
        return (Integer) iv.getTag();
    }
}
