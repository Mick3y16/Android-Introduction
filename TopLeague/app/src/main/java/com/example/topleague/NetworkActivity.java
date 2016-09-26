package com.example.topleague;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkActivity extends AppCompatActivity {

    /**
     * Contains the url to where to perform the request.
     */
    private EditText urlText;

    /**
     * Presents the results from the request made.
     */
    private TextView textView;

    /**
     * Performs the request of the information.
     */
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        this.urlText = (EditText) findViewById(R.id.editText);
        this.textView = (TextView) findViewById(R.id.textView);
        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringURL = NetworkActivity.this.urlText.getText().toString();
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebPageText().execute(stringURL);
                } else {
                    NetworkActivity.this.textView.setText("Connection unavailable.");
                }
            }
        });
    }

    private class DownloadWebPageText extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return downloadURL(strings[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // If we want to maintain information on the progress...
        }

        @Override
        protected void onPostExecute(String result) {
            NetworkActivity.this.textView.setText(result);
        }

        /**
         * Downloads the contents of a website returning them in a string.
         *
         * @param stringURL URL for the request.
         * @return String containing the contents of the request.
         */
        private String downloadURL(String stringURL) {
            try {
                URL url = new URL(stringURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                return convertStreamToString(httpURLConnection.getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return "";
        }
    }

    /**
     * Converts an input stream of data to string.
     *
     * @param inputStream Input stream of data.
     * @return Convert string.
     */
    private String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];

            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }

            return writer.toString();
        } else {
            return "";
        }

    }

}
