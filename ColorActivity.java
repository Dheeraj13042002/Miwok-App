package com.example.miwok;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.word_list);

        ArrayList<Word> colors = new ArrayList<>();

        colors.add(new Word("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("black", "kululli",R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("white", "kelelli",R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        WordAdapter adapter = new WordAdapter(this,colors,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(NumberActivity.this,"sdfghjui",Toast.LENGTH_SHORT).show();
                Word word = colors.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(ColorActivity.this,  word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
