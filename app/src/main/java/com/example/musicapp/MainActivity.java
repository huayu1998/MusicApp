package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button playButton;
    Spinner backgroundSpinner;
    Spinner overlappedSpin1;
    Spinner overlappedSpin2;
    Spinner overlappedSpin3;

    SeekBar seek1;
    SeekBar seek2;
    SeekBar seek3;

    int pro1 = 0;
    int pro2 = 0;
    int pro3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Background music spinner
        backgroundSpinner = findViewById(R.id.playSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.backgroungMusic, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);
        backgroundSpinner.setOnItemSelectedListener(this);

        // Overlapped music spinner
        overlappedSpin1 = findViewById(R.id.overlapSpin1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.overlapMusic, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overlappedSpin1.setAdapter(adapter1);
        overlappedSpin1.setOnItemSelectedListener(this);

        overlappedSpin2 = findViewById(R.id.overlapSpin2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.overlapMusic, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overlappedSpin2.setAdapter(adapter2);
        overlappedSpin2.setOnItemSelectedListener(this);

        overlappedSpin3 = findViewById(R.id.overlapSpin3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.overlapMusic, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overlappedSpin3.setAdapter(adapter3);
        overlappedSpin3.setOnItemSelectedListener(this);

        // Seek bar part
        seek1 = (SeekBar) findViewById(R.id.seekBar1);
        seek2 = (SeekBar) findViewById(R.id.seekBar2);
        seek3 = (SeekBar) findViewById(R.id.seekBar3);

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro1 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro2 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro3 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // The play button
        playButton = (Button) findViewById(R.id.playButton);

    }

    // Save the data
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("integer1", pro1);
        savedInstanceState.putInt("integer2", pro2);
        savedInstanceState.putInt("integer3", pro3);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pro1 = savedInstanceState.getInt("integer1");
        pro2 = savedInstanceState.getInt("integer2");
        pro3 = savedInstanceState.getInt("integer3");

        seek1.setProgress(pro1);
        seek2.setProgress(pro2);
        seek3.setProgress(pro3);
    }

    public void playMusic(View view) {

        Intent intent = new Intent(this, PlayActivity.class);
        String str1 = backgroundSpinner.getSelectedItem().toString();
        intent.putExtra("backgroundtext", str1);

        String str2 = overlappedSpin1.getSelectedItem().toString();
        intent.putExtra("spin1", str2);

        String str3 = overlappedSpin2.getSelectedItem().toString();
        intent.putExtra("spin2", str3);

        String str4 = overlappedSpin3.getSelectedItem().toString();
        intent.putExtra("spin3", str4);

        intent.putExtra("progress1", pro1);
        intent.putExtra("progress2", pro2);
        intent.putExtra("progress3", pro3);

        // Also send the data to MusicPlayer class
        Intent intentToMusicPlayer = new Intent(this, MusicPlayer.class);
        intentToMusicPlayer.putExtra("backgroundtext", str1);
        intentToMusicPlayer.putExtra("spin1", str2);
        intentToMusicPlayer.putExtra("spin2", str3);
        intentToMusicPlayer.putExtra("spin3", str4);
        intentToMusicPlayer.putExtra("progress1", pro1);
        intentToMusicPlayer.putExtra("progress2", pro2);
        intentToMusicPlayer.putExtra("progress3", pro3);
        // --------------------------------------------------

        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if ("Clapping".equals(overlappedSpin1.getSelectedItem().toString())) {
            seek1.setMax(new MediaPlayer().create(this, R.raw.clapping).getDuration());
        }
        else if ("Cheering".equals(overlappedSpin1.getSelectedItem().toString())) {
            seek1.setMax(new MediaPlayer().create(this, R.raw.cheering).getDuration());
        }
        else if ("Go Hokies!".equals(overlappedSpin1.getSelectedItem().toString())) {
            seek1.setMax(new MediaPlayer().create(this, R.raw.lestgohokies).getDuration());
        }

        if ("Clapping".equals(overlappedSpin2.getSelectedItem().toString())) {
            seek2.setMax(new MediaPlayer().create(this, R.raw.clapping).getDuration());
        }
        else if ("Cheering".equals(overlappedSpin2.getSelectedItem().toString())) {
            seek2.setMax(new MediaPlayer().create(this, R.raw.cheering).getDuration());
        }
        else if ("Go Hokies!".equals(overlappedSpin2.getSelectedItem().toString())) {
            seek2.setMax(new MediaPlayer().create(this, R.raw.lestgohokies).getDuration());
        }

        if ("Clapping".equals(overlappedSpin3.getSelectedItem().toString())) {
            seek3.setMax(new MediaPlayer().create(this, R.raw.clapping).getDuration());
        }
        else if ("Cheering".equals(overlappedSpin3.getSelectedItem().toString())) {
            seek3.setMax(new MediaPlayer().create(this, R.raw.cheering).getDuration());
        }
        else if ("Go Hokies!".equals(overlappedSpin3.getSelectedItem().toString())) {
            seek3.setMax(new MediaPlayer().create(this, R.raw.lestgohokies).getDuration());
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}