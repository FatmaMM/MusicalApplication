package com.example.android.musicalapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button openSongList=(Button)findViewById(R.id.openSongList);
        openSongList.setOnClickListener(this);
        Button openPhotActivity=(Button)findViewById(R.id.openPhotActivity);
        openPhotActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openSongList: {
                Intent intent = new Intent(AboutActivity.this, MaherActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.openPhotActivity :{
                Intent intent=new Intent(AboutActivity.this,PhotosActivity.class);
                startActivity(intent);
            }
            break;

        }
    }
}
