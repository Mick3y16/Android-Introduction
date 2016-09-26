package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item_view);
        TextView textViewCountry = (TextView) findViewById(R.id.product_label);
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");

        // Show the name of the selected country.
        textViewCountry.setText(country);
    }
}
