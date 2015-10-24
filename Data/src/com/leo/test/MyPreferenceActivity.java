package com.leo.test;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by LT on 2015-05-21.
 */
public class MyPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_setting);
    }
}
