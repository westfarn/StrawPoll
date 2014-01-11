package com.android.strawpoll;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by westf_000 on 12/15/13.
 * This file is the pollObjectButton functionality.
 * ToDo:
 *   1. get information from database
 *   2. create poll_object_buttons
 *   3. set click event for each poll
 *   4. transition to viewPollInformation page
 */
public class ViewListOfPolls extends Activity implements View.OnClickListener{

    List<PollObject> listOfpobs = new ArrayList<PollObject>();
    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_of_polls);

        if (savedInstanceState == null) {
            //getFragmentManager().beginTransaction()
            //        .add(R.id.view_polls_container, new MainMenu.PlaceholderFragment())
            //        .commit();
        }

        //get information from database and create polls

        //this is temporary and only for testing
        //ScrollView scrollView = (ScrollView)findViewById(R.id.view_polls_scroll_view);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.view_polls_linear_layout);
        for(int i = 0; i < 5; i++) {
            int type = i % 3;
            String strType = "";
            if(type == 1) { strType = "generic"; }
            else if(type == 2) { strType = "location"; }
            else if(type == 3) { strType = "picture"; }
            PollObject pob;
            if (i == 4 ) {
                pob = new PollObject(this,i,strType,"A Poll Title",true);
            }else {
                pob = new PollObject(this,i,strType,"A Poll Title");
            }
            pob.setOnClickListener(this);
            /*pob.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    return true;
                }
            });*/
            listOfpobs.add(pob);
            if(linearLayout == null){
                Log.d("loading","linearLayout is null");
            }else{
                linearLayout.addView(pob);
            }

        }



    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
            return rootView;
        }
    }

    public void onClick(View v) {
        // we want to go to another page with this information being displayed
        for(int i = 0; i < listOfpobs.size(); i++)
        {
            if(v == listOfpobs.get(i)){
                listOfpobs.get(i).changePicture();
                //Toast.makeText(this, "you clicked " + Integer.toString(listOfpobs.get(i).getID()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ViewPoll.class);
                String messageToPass = listOfpobs.get(i).getPollTitle();
                intent.putExtra(EXTRA_MESSAGE, messageToPass);
                startActivity(intent);
            }
        }


    }
}
