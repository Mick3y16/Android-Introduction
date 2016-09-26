package com.example.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Checkbox used to determine if a person plays football.
     */
    private CheckBox footballCheckBox;

    /**
     * Checkbox used to determine if a person listens to music.
     */
    private CheckBox musicCheckBox;

    /**
     * Checkbox used to determine if a person goes to the beach.
     */
    private CheckBox beachCheckBox;

    /**
     * Checkbox used to determine if a person doesn't have any hobbies.
     */
    private CheckBox noHobbiesCheckBox;

    /**
     * Button used to send the hobbies information.
     */
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.footballCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        this.musicCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        this.beachCheckBox = (CheckBox) findViewById(R.id.checkBox3);
        this.noHobbiesCheckBox = (CheckBox) findViewById(R.id.checkBox4);

        this.noHobbiesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean state = true;

                // If the checkbox is checked we need to disable all the others.
                if (((CheckBox) view).isChecked()) {
                    state = false;
                }

                MainActivity.this.footballCheckBox.setEnabled(state);
                MainActivity.this.musicCheckBox.setEnabled(state);
                MainActivity.this.beachCheckBox.setEnabled(state);
            }
        });

        this.sendButton = (Button) findViewById(R.id.button);

        this.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.this.noHobbiesCheckBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "No hobbies!", Toast.LENGTH_LONG).show();
                } else {
                    StringBuffer result = new StringBuffer();

                    if (MainActivity.this.footballCheckBox.isChecked())
                        result.append(MainActivity.this.footballCheckBox.getText()).append("\n");
                    if (MainActivity.this.musicCheckBox.isChecked())
                    result.append(MainActivity.this.musicCheckBox.getText()).append("\n");
                    if (MainActivity.this.beachCheckBox.isChecked())
                        result.append(MainActivity.this.beachCheckBox.getText()).append("\n");

                    Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
