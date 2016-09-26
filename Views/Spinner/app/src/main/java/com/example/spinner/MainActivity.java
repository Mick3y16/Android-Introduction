package com.example.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Spinner containing a list of browsers.
     */
    private Spinner spinnerBrowsers;

    /**
     * Text view presenting the browser selected.
     */
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.browsers, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerBrowsers = (Spinner) findViewById(R.id.spinner);
        this.spinnerBrowsers.setOnItemSelectedListener(this);
        this.spinnerBrowsers.setAdapter(arrayAdapter);

        this.textViewResult = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        this.textViewResult.setText("You selected " + adapterView.getSelectedItem().toString() + ".");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        this.textViewResult.setText("No browser selected.");
    }
}
