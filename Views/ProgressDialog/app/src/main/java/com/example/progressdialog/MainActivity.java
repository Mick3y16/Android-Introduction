package com.example.progressdialog;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * Button that begins the download.
     */
    private Button downloadButton;

    /**
     * Dialog where the progress of the download is shown.
     */
    private ProgressDialog progressDialog;

    /**
     * Status of the download (progress).
     */
    private int progressBarStatus = 0;

    /**
     * Handler of the progress bar.
     */
    private Handler progressBarHandler = new Handler();

    /**
     * Size of the downloaded file.
     */
    private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        this.downloadButton = (Button) findViewById(R.id.button);
        this.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Defining the progress dialog
                MainActivity.this.progressDialog = new ProgressDialog(view.getContext());
                MainActivity.this.progressDialog.setCancelable(true);
                MainActivity.this.progressDialog.setMessage("File downloading...");
                MainActivity.this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                MainActivity.this.progressDialog.setProgress(0);
                MainActivity.this.progressDialog.setMax(0);
                MainActivity.this.progressDialog.show();
                MainActivity.this.progressBarStatus = 0;
                MainActivity.this.fileSize = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (MainActivity.this.progressBarStatus < 100) {
                            // Runs the task simulator.
                            MainActivity.this.progressBarStatus = doSomeTasks();

                            // Sleeps for 1 second.
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            // Updates the progress bar.
                            MainActivity.this.progressBarHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.this.progressDialog.setProgress(MainActivity.this.progressBarStatus);
                                }
                            });
                        }

                        // After the simulated download
                        if (MainActivity.this.progressBarStatus >= 100) {
                            // Wait 2 seconds for the indication of 100%.
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            // Closes the dialog box.
                            MainActivity.this.progressDialog.dismiss();
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * Increment the file size variable 10% at a time.
     *
     * @return File size download percentage.
     */
    private int doSomeTasks() {
        this.fileSize++;

        while (this.fileSize <= 1000000 && this.fileSize % 100000 != 0) {
            this.fileSize++;
        }

        return (int) (this.fileSize / 10000);
    }


}
