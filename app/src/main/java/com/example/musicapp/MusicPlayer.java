package com.example.musicapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;

public class MusicPlayer implements MediaPlayer.OnCompletionListener {

    MediaPlayer musicplayer;
    int currentPosition = 0;
    int musicIndex = 0;
    private int musicStatus = 0;//0: before playing, 1 playing, 2 paused
    private MusicService musicService;


    static final int[] MUSICPATH = new int[]{
            R.raw.gotechgo,
            R.raw.mario,
            R.raw.tetris,
            R.raw.clapping,
            R.raw.cheering,
            R.raw.lestgohokies
    };

    static final String[] MUSICNAME = new String[]{
            "GoTechGo!",
            "Mario!",
            "Tetris",
            "Clapping",
            "Cheering",
            "Go Hokies!"
    };

    //public MusicPlayer(MusicService service) {
        //this.musicService = service;
   // }
    public MusicPlayer() {

    }

    public int getMusicStatus() {
        return musicStatus;
    }

    public String getMusicName() {
        return MUSICNAME[musicIndex];
    }

    public void playMusic(String musicName, int position) {

        for (int i = 0; i < MUSICNAME.length; i++) {
            if (musicName.equals(MUSICNAME[i])) {
                musicplayer = MediaPlayer.create(this.musicService, MUSICPATH[i]);
                break;
            }
        }
        musicplayer.seekTo(position);
        currentPosition = position;
        musicplayer.start();
        musicplayer.setOnCompletionListener(this);
        //musicService.onUpdateMusicName(getMusicName());
        musicStatus = 1;
    }

    public void pauseMusic() {
        if(musicplayer!= null && musicplayer.isPlaying()){
            musicplayer.pause();
            currentPosition = musicplayer.getCurrentPosition();
            musicStatus= 2;
        }
    }

    public void resumeMusic() {
        if(musicplayer!= null){
            musicplayer.seekTo(currentPosition);
            musicplayer.start();
            musicStatus=1;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //musicIndex = (musicIndex +1) % MUSICNAME.length;
        musicplayer.release();
        musicplayer= null;
        //playMusic();
    }


}
