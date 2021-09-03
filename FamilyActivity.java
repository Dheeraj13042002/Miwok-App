package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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

        ArrayList<Word> families = new ArrayList<>();

        families.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        families.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        families.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        families.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        families.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        families.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        families.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        families.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        families.add(new Word("grandmother", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        families.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this,families,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = families.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,  word.getAudioResourceId());
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
