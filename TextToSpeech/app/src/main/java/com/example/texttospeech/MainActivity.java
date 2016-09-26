package com.example.texttospeech;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /**
     * Converts text to speech.
     */
    private TextToSpeech textToSpeech;

    /**
     * View where the text is written to be converted.
     */
    private EditText editText;

    /**
     * Button that triggers the TTS functionality.
     */
    private Button button;

    /**
     * Code that states the conversion was successful.
     */
    private int MY_CHECK_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.editText);
        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToConvert = MainActivity.this.editText.getText().toString();

                if (textToConvert != null && !textToConvert.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Saying: " + textToConvert, Toast.LENGTH_SHORT).show();
                    MainActivity.this.textToSpeech.speak(textToConvert, TextToSpeech.QUEUE_ADD, null);
                } else {
                    Toast.makeText(MainActivity.this, "Please write some text first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, MY_CHECK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) { // we have support for TTS.
                this.textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            MainActivity.this.textToSpeech.setLanguage(Locale.UK);
                            Toast.makeText(MainActivity.this, "TTS started.", Toast.LENGTH_LONG).show();
                        } else if (status == TextToSpeech.ERROR) {
                            Toast.makeText(MainActivity.this, "Error starting TTS.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else { // we need to install it.
                Intent installIntent = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

}
