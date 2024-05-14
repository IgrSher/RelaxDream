package com.example.relaxdreamv2;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class ListenerNature extends Fragment {
    Button GoToList, GoToMenu;
    ImageButton Start, Pause, Next, Previous, Continue;
    MediaPlayer currentSound, song1, song2, song3,song4,song5;
    MediaPlayer[] songList, melodyList, natureList;
    String[] natures;
    SeekBar songDuration;

    TextView currentTime, totalTime;

    int currentCategory;

    public String currentSongName;

    public int currentIndex = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listener_nature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Start = (ImageButton) view.findViewById(R.id.Start);
        Pause = (ImageButton) view.findViewById(R.id.Pause);
        Next = (ImageButton) view.findViewById(R.id.Next);
        Previous = (ImageButton) view.findViewById(R.id.Previous);
        Continue = (ImageButton) view.findViewById(R.id.Continue);
        GoToList =  view.findViewById(R.id.goToList);
        GoToMenu = view.findViewById(R.id.gotomain);
        song1 = MediaPlayer.create(getContext(), R.raw.fire);
        song2 = MediaPlayer.create(getContext(), R.raw.forestevening);
        song3 = MediaPlayer.create(getContext(), R.raw.glubokovlesu);
        song4 = MediaPlayer.create(getContext(),R.raw.nightbirds);
        song5 = MediaPlayer.create(getContext(),R.raw.osenniepticy);

        songDuration = view.findViewById(R.id.songDuration);
        currentTime = view.findViewById(R.id.currentTimer);
        totalTime = view.findViewById(R.id.totalTimer);

        currentIndex = 0;
        songList = new MediaPlayer[] {song1, song2, song3,song4,song5};
        currentSound = songList[currentIndex];

        natures = new String[] {"", "", "","",""};
        currentSongName = natures[currentIndex];

        Start.setOnClickListener(v -> {
            soundPlayButton(currentSound);
            Start.setVisibility(View.INVISIBLE);
            Pause.setVisibility(View.VISIBLE);
            TextView name = (TextView) view.findViewById(R.id.songName);
            name.setText(currentSongName);
            onPrepared(currentSound);
        });
        Pause.setOnClickListener(v -> {
            soundStopButton(currentSound);
            Pause.setVisibility(View.INVISIBLE);
            Continue.setVisibility(View.VISIBLE);
            onPrepared(currentSound);
        });
        Continue.setOnClickListener(v -> {
            soundContinueButton(currentSound);
            Pause.setVisibility(View.VISIBLE);
            Continue.setVisibility(View.INVISIBLE);
            onPrepared(currentSound);
        });
        Next.setOnClickListener(v -> {
            if (currentIndex != 4){
                soundStopButton(currentSound);
                currentIndex+=1;
                currentSound = songList[currentIndex];
                currentSongName = natures[currentIndex];
                soundPlayButton(currentSound);
                TextView name = (TextView) view.findViewById(R.id.songName);
                name.setText(currentSongName);
                Pause.setVisibility(View.VISIBLE);
                Continue.setVisibility(View.INVISIBLE);
                onPrepared(currentSound);
                songDuration.setProgress(0);
            }
            else {
                currentIndex = 0;
                soundStopButton(currentSound);
                currentSound = songList[currentIndex];
                currentSongName = natures[currentIndex];
                soundPlayButton(currentSound);
                TextView name = (TextView) view.findViewById(R.id.songName);
                name.setText(currentSongName);}
            Pause.setVisibility(View.VISIBLE);
            Continue.setVisibility(View.INVISIBLE);
            onPrepared(currentSound);
            songDuration.setProgress(0);
        });
        Previous.setOnClickListener(v -> {
            if (currentIndex != 0) {
                soundStopButton(currentSound);
                currentIndex -= 1;
                currentSound = songList[currentIndex];
                currentSongName = natures[currentIndex];
                soundPlayButton(currentSound);
                TextView name = (TextView) view.findViewById(R.id.songName);
                name.setText(currentSongName);
                Pause.setVisibility(View.VISIBLE);
                Continue.setVisibility(View.INVISIBLE);
                onPrepared(currentSound);
                songDuration.setProgress(0);
            } else {
                soundStopButton(currentSound);
                currentIndex = 4;
                currentSound = songList[currentIndex];
                currentSongName = natures[currentIndex];
                soundPlayButton(currentSound);
                TextView name = (TextView) view.findViewById(R.id.songName);
                name.setText(currentSongName);
                Pause.setVisibility(View.VISIBLE);
                Continue.setVisibility(View.INVISIBLE);
                onPrepared(currentSound);
                songDuration.setProgress(0);
            }
        });
        GoToList.setOnClickListener(v1 -> {
            Navigation.findNavController(view).navigate(R.id.action_listenerNature_to_natureList);
        });
        GoToMenu.setOnClickListener(v1 -> {
            currentSound.stop();
            Navigation.findNavController(view).navigate(R.id.action_listenerNature_to_mainMenu);
        });

    }
    private void soundPlayButton(MediaPlayer sound) {
        if (sound.isPlaying()){
            sound.stop();
        }
        sound.seekTo(0);
        sound.start();
    }
    private void soundStopButton(MediaPlayer sound) {
        sound.pause();
    }
    private void soundContinueButton(MediaPlayer sound){
        sound.start();
    }
    public void onPrepared(MediaPlayer sound) {
        songDuration.setMax(sound.getDuration());
        songDuration.postDelayed(onEverySecond, 1);
        totalTime.setText(createTimeLabel(sound.getDuration()));
        sound.seekTo(songDuration.getProgress());
    }
    private Runnable onEverySecond = new Runnable() {

        @Override
        public void run() {

            if (songDuration != null) {
                songDuration.setProgress(currentSound.getCurrentPosition());
                currentTime.setText(createTimeLabel(currentSound.getCurrentPosition()));
            }

            if (currentSound.isPlaying()) {
                songDuration.postDelayed(onEverySecond, 1000);
                currentTime.setText(createTimeLabel(currentSound.getCurrentPosition()));
            }
        }
    };
    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }
}