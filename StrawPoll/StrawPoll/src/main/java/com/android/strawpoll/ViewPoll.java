package com.android.strawpoll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by westf_000 on 12/20/13.
 * This file is the ViewPoll functionality.  There is where the user will interact with the poll
 * to send a response.
 * ToDo:
 *  1. allow a person to open up google maps from here
 *  2. get all relevant information
 */

public class ViewPoll extends Activity implements View.OnClickListener{

    //public members

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_poll);

        //get the message that has been passed
        Intent intent = getIntent();
        //String message = intent.getStringExtra(ViewListOfPolls.EXTRA_MESSAGE);

        setPollTitle(intent.getStringExtra(ViewListOfPolls.EXTRA_MESSAGE));

        if (savedInstanceState == null) {
            //getFragmentManager().beginTransaction()
            //        .add(R.id.view_polls_container, new MainMenu.PlaceholderFragment())
            //        .commit();
        }
    }

    /*------------ setPollTitle -----------
          Sets the title at the top for the poll
    */
    private void setPollTitle(String message) {
        TextView textView = (TextView)findViewById(R.id.view_poll_title);
        textView.setText(message);
    }

    /*------------ onClick -----------
          once a user clicks, this send a response back to the creator
    */
    public void onClick(View v) {
        //nothing right now.
    }
}
