package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

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

        final ArrayList<Word> fam = new ArrayList<>();


        fam.add(new Word("one", "lutti",R.drawable.number_one, R.raw.number_one));
        fam.add(new Word("two", "otiiko",R.drawable.number_two, R.raw.number_two));
        fam.add(new Word("three", "tolookosu",R.drawable.number_three, R.raw.number_three));
        fam.add(new Word("four", "oyyisa",R.drawable.number_four, R.raw.number_four));
        fam.add(new Word("five", "massokka",R.drawable.number_five, R.raw.number_five));
        fam.add(new Word("six", "temmokka",R.drawable.number_six, R.raw.number_six));
        fam.add(new Word("seven", "kenekaku",R.drawable.number_seven, R.raw.number_seven));
        fam.add(new Word("eight", "kawinta",R.drawable.number_eight, R.raw.number_eight));
        fam.add(new Word("nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
        fam.add(new Word("ten", "na’aacha",R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this,fam,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(NumberActivity.this,"sdfghjui",Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();
                Word word = fam.get(position);

                mMediaPlayer = MediaPlayer.create(NumberActivity.this,  word.getAudioResourceId());
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



    /**
     * Clean up the media player by releasing its resources.
     */
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
