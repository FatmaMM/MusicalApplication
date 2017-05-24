package com.example.android.musicalapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MaherActivity extends AppCompatActivity {
    ListView maherListView;
    List<String> maherList;
    ListAdapter maherAdapter;
    MediaPlayer mediaPlayer;
    String name = "";
    int resID = 0;
    SeekBar maherSeekBar;
    Button playButton;
    Button pauseButton;
    Button stopButton;

    private final Handler handler = new Handler();

    private final Runnable updatePositionRunnable = new Runnable() {
        public void run() {
            updatePosition();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maher);

        maherListView = (ListView) findViewById(R.id.mahermenu);
        maherList = new ArrayList<>();
        maherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, maherList);
        maherListView.setAdapter(maherAdapter);

        playButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);

        maherSeekBar = (SeekBar) findViewById(R.id.myseekBar);

        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {

            maherList.add(fields[i].getName());
        }
        maherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                resID = getResources().getIdentifier(maherList.get(position), "raw", getPackageName());
                name = parent.getItemAtPosition(position).toString();
                TextView textView = (TextView) findViewById(R.id.media_text);
                textView.setText(name);
                maherSeekBar.setProgress(0);
                mediaPlayer = MediaPlayer.create(MaherActivity.this, resID);
                mediaPlayer.start();
                updatePosition();

                maherSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mediaPlayer.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                updatePosition();

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(MaherActivity.this, resID);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                handler.removeCallbacks(updatePositionRunnable);
            }
        });

    }

    private void updatePosition() {
        handler.removeCallbacks(updatePositionRunnable);
        maherSeekBar.setMax(mediaPlayer.getDuration());
        maherSeekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.getDuration()==mediaPlayer.getCurrentPosition()){
            mediaPlayer = MediaPlayer.create(MaherActivity.this, resID);
        }
        handler.postDelayed(updatePositionRunnable, 50);

    }

}
