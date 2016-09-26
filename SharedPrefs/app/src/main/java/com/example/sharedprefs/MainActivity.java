package com.example.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Shared preferences objects, where key/value data is stored.
     */
    private SharedPreferences sharedPreferences;

    /**
     * Name of the file contaning the preferences.
     */
    private String preferencesName;

    /**
     * Edit text where text can be written.
     */
    private EditText editTextSentence;

    /**
     * Bar used to modify the font size.
     */
    private SeekBar seekBarTextSize;

    /**
     * Button the update the shared preferences.
     */
    private Button buttonSave;

    /**
     * Name for the font size key/value pair.
     */
    private static final String FONT_SIZE_KEY = "fontsize";

    /**
     * Name for the text written key/value pair.
     */
    private static final String TEXT_VALUE_KEY = "textvalue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextSentence = (EditText) findViewById(R.id.editText);
        this.seekBarTextSize = (SeekBar) findViewById(R.id.seekBar);
        this.seekBarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Change the size of the text in the edit text.
                MainActivity.this.editTextSentence.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing...
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing...
            }
        });
        this.buttonSave = (Button) findViewById(R.id.button);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.sharedPreferences = getSharedPreferences(MainActivity.this.preferencesName, MODE_PRIVATE);
                SharedPreferences.Editor editor = MainActivity.this.sharedPreferences.edit();
                editor.putFloat(FONT_SIZE_KEY, MainActivity.this.editTextSentence.getTextSize());
                editor.putString(TEXT_VALUE_KEY, MainActivity.this.editTextSentence.getText().toString());
                editor.commit();

                Toast.makeText(getBaseContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        // Loading of the shared preferences object to fill both views.
        this.sharedPreferences = getSharedPreferences(MainActivity.this.preferencesName, MODE_PRIVATE);

        this.seekBarTextSize.setProgress((int) this.sharedPreferences.getFloat(FONT_SIZE_KEY, 12));
        this.editTextSentence.setText(this.sharedPreferences.getString(TEXT_VALUE_KEY, ""));
        this.editTextSentence.setTextSize(this.seekBarTextSize.getProgress());
    }

}
