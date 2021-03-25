package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    TextView backgroundMusicText;
    private MusicService musicService;
    MediaPlayer musicplayer;
    Intent mainIntent;
    Button playOrPause;
    ImageView pic;

    private Handler myHandler = new Handler();

    MediaPlayer spin1player;
    MediaPlayer spin2player;
    MediaPlayer spin3player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        backgroundMusicText = (TextView) findViewById(R.id.backgroundText);
        mainIntent = getIntent();
        backgroundMusicText.setText(mainIntent.getStringExtra("backgroundtext"));


        pic = (ImageView) findViewById(R.id.musicImage);
        if ("GoTechGo!".equals(backgroundMusicText.getText())) {
            pic.setImageResource(R.drawable.gohokies);
        }
        else if ("Mario!".equals(backgroundMusicText.getText())) {
            pic.setImageResource(R.drawable.mario);
        }
        else if ("Tetris!".equals(backgroundMusicText.getText())) {
            pic.setImageResource(R.drawable.tetris);
        }

        playOrPause = (Button) findViewById(R.id.playpause);

    }

    public void playNPause(View v) {

        // Overlapping music
        // 1

        if (musicplayer == null && spin1player == null) {
            if ("Clapping".equals(mainIntent.getStringExtra("spin1"))) {
                spin1player = MediaPlayer.create(this, R.raw.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin1"))) {
                spin1player = MediaPlayer.create(this, R.raw.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin1"))) {
                spin1player = MediaPlayer.create(this, R.raw.lestgohokies);
            }
            spin1player.seekTo(mainIntent.getIntExtra("progress1", 0));
            myHandler.postDelayed(myRunnable1, mainIntent.getIntExtra("progress1", 0));
        }


        // 2

        if (musicplayer == null && spin2player == null) {
            if ("Clapping".equals(mainIntent.getStringExtra("spin2"))) {
                spin2player = MediaPlayer.create(this, R.raw.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin2"))) {
                spin2player = MediaPlayer.create(this, R.raw.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin2"))) {
                spin2player = MediaPlayer.create(this, R.raw.lestgohokies);
            }

            spin2player.seekTo(mainIntent.getIntExtra("progress2", 0));
            myHandler.postDelayed(myRunnable2, mainIntent.getIntExtra("progress2", 0));
        }

        // 3

        if (musicplayer == null && spin3player == null) {
            if ("Clapping".equals(mainIntent.getStringExtra("spin3"))) {
                spin3player = MediaPlayer.create(this, R.raw.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin3"))) {
                spin3player = MediaPlayer.create(this, R.raw.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin3"))) {
                spin3player = MediaPlayer.create(this, R.raw.lestgohokies);
            }

            spin3player.seekTo(mainIntent.getIntExtra("progress3", 0));
            myHandler.postDelayed(myRunnable3, mainIntent.getIntExtra("progress3", 0));
        }

        // main music
        if (musicplayer == null) {

            if ("GoTechGo!".equals(backgroundMusicText.getText())) {
                musicplayer = MediaPlayer.create(this, R.raw.gotechgo);
            } else if ("Mario!".equals(backgroundMusicText.getText())) {
                musicplayer = MediaPlayer.create(this, R.raw.mario);
            } else if ("Tetris!".equals(backgroundMusicText.getText())) {
                musicplayer = MediaPlayer.create(this, R.raw.tetris);
            }
            musicplayer.start();
            playOrPause.setText("Pause");
            musicplayer.setOnCompletionListener(this);

        } else if (musicplayer != null && musicplayer.isPlaying()) {
            musicplayer.pause();
            if (spin1player != null) {
                spin1player.pause();
            }
            if (spin2player != null) {
                spin2player.pause();
            }
            if (spin3player != null) {
                spin3player.pause();
            }

            playOrPause.setText("Play");

        } else if (musicplayer != null && !musicplayer.isPlaying()) {
            musicplayer.start();
            if (spin1player != null) {
                spin1player.start();
            }
            if (spin2player != null) {
                spin2player.start();
            }
            if (spin3player != null) {
                spin3player.start();
            }
            playOrPause.setText("Pause");
        }

    }

    public void reStart(View v) {
        if (musicplayer!= null) {
            musicplayer.release();
            spin1player.release();
            spin2player.release();
            spin3player.release();
            musicplayer = null;
            spin1player = null;
            spin2player = null;
            spin3player = null;
            playOrPause.setText("Play");
            Toast.makeText(this, "Music released!", Toast.LENGTH_SHORT).show();

            if ("GoTechGo!".equals(backgroundMusicText.getText())) {
                pic.setImageResource(R.drawable.gohokies);
            }
            else if ("Mario!".equals(backgroundMusicText.getText())) {
                pic.setImageResource(R.drawable.mario);
            }
            else if ("Tetris!".equals(backgroundMusicText.getText())) {
                pic.setImageResource(R.drawable.tetris);
            }
        }

    }

    @Override
    public void onCompletion(MediaPlayer media) {
        media = null;
        media.release();
        playOrPause.setText("Play");

    }

    private Runnable myRunnable1 = new Runnable() {
        @Override
        public void run() {
            spin1player.start();
            if ("Clapping".equals(mainIntent.getStringExtra("spin1"))) {
                pic.setImageResource(R.drawable.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin1"))) {
                pic.setImageResource(R.drawable.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin1"))) {
                pic.setImageResource(R.drawable.letgohokies);
            }
        }
    };

    private Runnable myRunnable2 = new Runnable() {
        @Override
        public void run() {
            spin2player.start();
            if ("Clapping".equals(mainIntent.getStringExtra("spin2"))) {
                pic.setImageResource(R.drawable.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin2"))) {
                pic.setImageResource(R.drawable.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin2"))) {
                pic.setImageResource(R.drawable.letgohokies);
            }
        }
    };

    private Runnable myRunnable3 = new Runnable() {
        @Override
        public void run() {
            spin3player.start();
            if ("Clapping".equals(mainIntent.getStringExtra("spin3"))) {
                pic.setImageResource(R.drawable.clapping);
            } else if ("Cheering".equals(mainIntent.getStringExtra("spin3"))) {
                pic.setImageResource(R.drawable.cheering);
            } else if ("Go Hokies!".equals(mainIntent.getStringExtra("spin3"))) {
                pic.setImageResource(R.drawable.letgohokies);
            }
        }
    };

}