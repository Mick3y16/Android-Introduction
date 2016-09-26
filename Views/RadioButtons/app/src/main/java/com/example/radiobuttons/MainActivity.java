package com.example.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Text message presenting the selection.
     */
    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textMessage = (TextView) findViewById(R.id.txtMessage);
    }

    public void sendMessage(View view) {
        this.textMessage.setText("The user is " + ((RadioButton) view).getText() + ".");
    }

}
