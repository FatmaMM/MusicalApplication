package com.example.android.musicalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button photo=(Button)findViewById(R.id.photosButton);
        photo.setOnClickListener(this);
        Button about=(Button)findViewById(R.id.aboutButton);
        about.setOnClickListener(this);
        CardView cardView=(CardView)findViewById(R.id.card_view);
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.aboutButton: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.photosButton: {
                Intent intent = new Intent(MainActivity.this, PhotosActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.card_view: {
                Intent maherIntent = new Intent(this, MaherActivity.class);
                startActivity(maherIntent);
            }

            break;

            default:
                break;
        }
    }
}
