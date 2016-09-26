package com.example.tictactoe;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Represents a settings activity where changes can be made to improve game experience.
 *
 * @author pedro
 */
public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
