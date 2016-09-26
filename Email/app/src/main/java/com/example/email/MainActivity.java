package com.example.email;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Keeps the destination of the emails.
     */
    private EditText editText;

    /**
     * Triggers the sending of the email.
     */
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.editTextTo);
        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.this.editText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please write the destination email.", Toast.LENGTH_SHORT).show();
                } else {
                    String[] to = MainActivity.this.editText.getText().toString().split(";");
                    String[] cc = new String[] { "pedromiguelfmoreira@gmail.com" };

                    sendEmail(to, cc);
                }
            }
        });

    }

    /**
     * Sends a test email to a given list of emails.
     */
    private void sendEmail(String[] emailAddresses, String[] carbonCopies) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
        emailIntent.putExtra(Intent.EXTRA_CC, carbonCopies);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Test message...");
        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Email"));
    }

}
