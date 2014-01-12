package com.android.strawpoll;

/**
 * Created by Joe on 1/8/14.
 */
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;

public class OpenPoll extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_poll);
    }
}