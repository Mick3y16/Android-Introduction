package com.example.listview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Copy each element of the list into an array.
        String[] countryList = getResources().getStringArray(R.array.countries);

        // Bind the array to a list adapter.
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, countryList));
        ListView listView = getListView();

        // Handle clicking the window.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Selected item
                String country = ((TextView) view).getText().toString();

                // Invoke the new activity and send data to it after the list item selection.
                Intent intent = new Intent(getApplicationContext(), SingleListItem.class);
                intent.putExtra("country", country);
                startActivity(intent);
            }
        });
    }

}
