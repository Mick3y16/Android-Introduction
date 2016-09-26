package com.example.textfields;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Edit Text with a listener for editions attached to it.
     */
    private EditText editText;

    /**
     * Auto Complete Text View with an array of suggestions attached to it.
     */
    private AutoCompleteTextView autoCompleteTextView;

    /**
     * Array of Strings containing the months of the year.
     */
    private static final String[] MONTHS = new String[] { "January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allows the edit text to check for edits done to itself.
        this.editText = (EditText) findViewById(R.id.search);
        this.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEND) {
                    Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT);

                    return true;
                }

                return false;
            }
        });

        // Allows autocompletion of text on the edit box.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MONTHS);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
