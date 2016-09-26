package com.example.contentproviders;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView contactView = (TextView) findViewById(R.id.textView);

        Cursor contactsCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        String name = "";
        if (contactsCursor.moveToFirst()) {
            int index = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            do {
                name += contactsCursor.getString(index) + "\n";
            } while (contactsCursor.moveToNext());

            contactView.setText(name);
        }

        contactsCursor.close();
    }
}
