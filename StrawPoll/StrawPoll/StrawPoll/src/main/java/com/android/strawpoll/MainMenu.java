package com.android.strawpoll;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class MainMenu extends Activity implements GestureDetector.OnGestureListener{

    //gesture listener
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
        //FrameLayout frameLayout = (FrameLayout)findViewById(R.id.container);
        //add gesture controls here
        /*frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });*/
        //gestureDetectorCompat = new GestureDetectorCompat(this,this);
        //gestureDetectorCompat.setOnDoubleTapListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
            return rootView;
        }
    }*/

    public void goToViewPolls(View v) {
        Intent intent = new Intent(this, ViewListOfPolls.class);
        startActivity(intent);
    }

    //below are the gesture listeners
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.gestureDetectorCompat.onTouchEvent(event);
        //Ve sure to call the superclass impelementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event){
        Log.d("GESTURES","onDown: "+event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
        Log.d("GESTURES","onFling: "+event1.toString()+event2.toString());
        return true;
    }

    @Override
    public  void onLongPress(MotionEvent event){
        Log.d("GESTURES","onLongPress: "+event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY){
        Log.d("GESTURES","onScroll: "+event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event){
        Log.d("GESTURES","onShowPress: "+event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event){
        Log.d("GESTURES","onSingleTapUp: "+event.toString());
        return true;
    }




}
