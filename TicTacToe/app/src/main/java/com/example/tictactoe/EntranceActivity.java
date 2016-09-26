package com.example.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Represents the entrance activity, responsible for showing a logo and the name of the game to the
 * user, loading the next activity after a bit.
 *
 * @author pedro
 */
public class EntranceActivity extends AppCompatActivity {

    /**
     * Time it takes to move to the next activity.
     */
    private static final Long DELAY_TIME = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        // Waits a bit before moving onto the next activity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), TicTacToeActivity.class));
                EntranceActivity.this.finish();
            }
        }, DELAY_TIME);
    }

}
