package com.example.android.chatapps;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class FragmentAboutActivity extends Fragment{
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    public FragmentAboutActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_about, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoView = (VideoView) getActivity().findViewById(R.id.videoView);

        if (mediaController == null) {
            mediaController = new MediaController(getContext());

            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);
        }

        String videopath = "android.resource://com.example.android.chatapps/" + R.raw.catatan_najwa;

        videoView.setVideoURI(Uri.parse(videopath));

        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.seekTo(position);
                if (position == 0) {
                }

                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

    }

}
