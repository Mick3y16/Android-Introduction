package com.example.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /**
     * Button responsible for taking the picture.
     */
    private Button button;

    /**
     * View responsible for showing the picture taken.
     */
    private ImageView imageView;

    /**
     * Folder to store the picture taken, name included.
     */
    private File file;

    /**
     * Request Code for image capturing.
     */
    private static final int IMAGE_CAPTURE = 0;

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
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "myPhoto.jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Bitmap captureBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(this.file));
                this.imageView.setImageBitmap(captureBitmap);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Starts the camera, to allow the taking of pictures.
     */
    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(this.file));
        startActivityForResult(intent, IMAGE_CAPTURE);

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(this.file));
        this.sendBroadcast(mediaScanIntent);
    }

}
