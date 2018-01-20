
package com.example.ayushib.menuapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;



public class WorkoutVideoActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    String TAG="WorkoutVideoActivity";
    public static final String API_KEY = "AIzaSyDs-0a2HWRzbw_6C9ht-YHLG7SzkkZR6rc";
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    YouTubePlayerFragment myYouTubePlayerFragment;
   /* YouTubePlayerView youTubePlayerView;
    String API_KEY="Your API Key";
    private static final int RECOVERY_REQUEST = 1;
    String TAG="VideoActivity"; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_video);

        myYouTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        myYouTubePlayerFragment.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Bundle bundle = getIntent().getExtras();
        String showVideo = bundle.getString("videoId");
        Log.e(TAG,"Video" +showVideo);
        youTubePlayer.cueVideo(showVideo);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "Error Intializing Youtube Player", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtubeplayerfragment);
    }
}

