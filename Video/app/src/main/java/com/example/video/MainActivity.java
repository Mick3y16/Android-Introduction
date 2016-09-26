package com.example.video;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    /**
     * Button that start the capture of the video.
     */
    private Button button;

    /**
     * View where the captured video is shown.
     */
    private VideoView videoView;

    /**
     * Video capture code.
     */
    private static final int VIDEO_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCamera();
            }
        });
        this.videoView = (VideoView) findViewById(R.id.videoView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Log.d("ANDRO_CAMERA", "Video Recorded!");

            this.videoView.setVideoURI(data.getData());
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(this.videoView);
            this.videoView.setMediaController(mediaController);
            this.videoView.start();
        }

    }

    private void startCamera() {
        Log.d("ANDRO_CAMERA", "Starting the recording camera in the device...");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }

}
