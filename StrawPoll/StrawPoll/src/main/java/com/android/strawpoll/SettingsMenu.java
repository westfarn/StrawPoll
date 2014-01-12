package com.android.strawpoll;

/**
 * Created by Joe on 1/8/14.
 */

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;

public class SettingsMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/
}
