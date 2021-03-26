package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Handler;

public class MusicPlayer implements MediaPlayer.OnCompletionListener {

    MediaPlayer player;
    int currentPosition = 0;
    //int musicIndex = 0;
    private int musicStatus = 0;//0: before playing, 1 playing, 2 paused
    private MusicService musicService;

    // My code
    MainActivity mainActivity = new MainActivity();
    String mainmusic = mainActivity.getBackgroundName();
    String spinmusic1 = mainActivity.getSpinName1();
    String spinmusic2 = mainActivity.getSpinName2();
    String spinmusic3 = mainActivity.getSpinName3();
    int proc1 = mainActivity.getPro1();
    int proc2 = mainActivity.getPro2();
    int proc3 = mainActivity.getPro3();
    MediaPlayer spinplayer1;
    MediaPlayer spinplayer2;
    MediaPlayer spinplayer3;

    private Handler myHandler = new Handler();

    //

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
            "Tetris!",
            "Clapping",
            "Cheering",
            "Go Hokies!"
    };

    public MusicPlayer(MusicService service) {

        this.musicService = service;
    }


    public int getMusicStatus() {

        return musicStatus;
    }

    public String getMusicName() {

        for (int i = 0; i < MUSICNAME.length; i++) {
            if (MUSICNAME[i].equals(mainmusic)) {
                return MUSICNAME[i];
            }
        }
        return "";
    }

    public void playMusic() {

        // My Code

        // main music
        if (player == null) {
            for (int i = 0; i < MUSICNAME.length; i++) {
                if (MUSICNAME[i].equals(mainmusic)) {
                    player = MediaPlayer.create(this.musicService, MUSICPATH[i]);
                    player.start();
                    player.setOnCompletionListener(this);
                    musicService.onUpdateMusicName(getMusicName());
                    musicStatus = 1;
                }
            }
        }

        // overlap music
        if (player == null && spinplayer1 == null) {
            for (int i = 0; i < MUSICNAME.length; i++) {
                if (MUSICNAME[i].equals(spinmusic1)) {
                    spinplayer1 = MediaPlayer.create(this.musicService, MUSICPATH[i]);
                }
                spinplayer1.seekTo(proc1);
                myHandler.postDelayed(myRunnable1, proc1);
            }
        }

        if (player == null && spinplayer2 == null) {
            for (int i = 0; i < MUSICNAME.length; i++) {
                if (MUSICNAME[i].equals(spinmusic2)) {
                    spinplayer2 = MediaPlayer.create(this.musicService, MUSICPATH[i]);
                }
                spinplayer2.seekTo(proc2);
                myHandler.postDelayed(myRunnable2, proc2);
            }
        }

        if (player == null && spinplayer3 == null) {
            for (int i = 0; i < MUSICNAME.length; i++) {
                if (MUSICNAME[i].equals(spinmusic3)) {
                    spinplayer3 = MediaPlayer.create(this.musicService, MUSICPATH[i]);
                }
                spinplayer3.seekTo(proc3);
                myHandler.postDelayed(myRunnable3, proc3);
            }
        }
        //
    }

    public void pauseMusic () {
        if (player != null && player.isPlaying()) {
            player.pause();
            currentPosition = player.getCurrentPosition();
            musicStatus = 2;
        }

        if (spinplayer1 != null && spinplayer1.isPlaying()) {
            spinplayer1.pause();
            proc1 = spinplayer1.getCurrentPosition();
        }
        else {
            spinplayer1 = null;
        }

        if (spinplayer2 != null && spinplayer2.isPlaying()) {
            spinplayer2.pause();
            proc2 = spinplayer2.getCurrentPosition();
        }
        else {
            spinplayer2 = null;
        }

        if (spinplayer3 != null && spinplayer3.isPlaying()) {
            spinplayer3.pause();
            proc3 = spinplayer3.getCurrentPosition();
        }
        else {
            spinplayer3 = null;
        }
    }

    public void resumeMusic () {
        if (player != null) {
            player.seekTo(currentPosition);
            player.start();
            musicStatus = 1;
        }

        if (spinplayer1 != null) {
            spinplayer1.seekTo(proc1);
            spinplayer1.start();
        }

        if (spinplayer2 != null) {
            spinplayer2.seekTo(proc2);
            spinplayer2.start();
        }

        if (spinplayer3 != null) {
            spinplayer3.seekTo(proc3);
            spinplayer3.start();
        }

    }

    @Override
    public void onCompletion (MediaPlayer mediaPlayer){
        //musicIndex = (musicIndex +1) % MUSICNAME.length;
        player.release();
        player = null;
        //playMusic();
    }

    private Runnable myRunnable1 = new Runnable() {
        @Override
        public void run() {
            spinplayer1.start();
        }
    };

    private Runnable myRunnable2 = new Runnable() {
        @Override
        public void run() {
            spinplayer2.start();
        }
    };

    private Runnable myRunnable3 = new Runnable() {
        @Override
        public void run() {
            spinplayer3.start();
        }
    };
}