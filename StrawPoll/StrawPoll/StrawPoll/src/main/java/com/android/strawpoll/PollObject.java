package com.android.strawpoll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by westf_000 on 12/15/13.
 * This file is the pollObjectButton functionality.
 * ToDo:
 *   1. change the picture
 *   2. send the data of the object to the parent activity so we can open the poll
 *   3. take the dynamic data as input as a constructor
 */
public class PollObject extends LinearLayout {

    //member of the class
    int id;
    String type = "";
    String pollTitle = "";

    public PollObject(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.poll_object, this, true);

        //do extra stuff
    }

    public PollObject(Context context) {
        this(context, null);
    }

    public PollObject(Context context, int number, String type, String title) {
        this(context, null);
        this.id = number;
        this.type = type;
        setPollTitle(title);
    }

    public PollObject(Context context, int number, String type, String title, Boolean createdByUser) {
        this(context, null);
        this.id = number;
        this.type = type;
        setPollTitle(title);
        if (createdByUser){ setAsPollUserCreated(); }
    }

    /*------------ addNewPicture -----------
          sets the default image file for the poll on creation
    */
    public void addNewPicture() {
        ImageView imgView = (ImageView)findViewById(R.id.poll_object_btn_image);
        Resources res = getContext().getResources();
        if(this.type == "generic") {
            //add the new pciture for the generic type
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.generic_new);
            imgView.setImageBitmap(bitmap);
        }else if(this.type == "location") {
            //add the new picture for the location type
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.generic_new);
            imgView.setImageBitmap(bitmap);
        }else if(this.type == "picture") {
            //add the new picture for the picture type
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.generic_new);
            imgView.setImageBitmap(bitmap);
        }
    }

    /*------------ changePicture -----------
          change the picture of the object
          This is invoked when a user opens the poll
    */
    public void changePicture() {
        ImageView imgView = (ImageView)findViewById(R.id.poll_object_btn_image);
        Resources res = getContext().getResources();
        if(type == "generic") {
            //add the new picture for the generic type
            Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.generic_view);
            imgView.setImageBitmap(bitmap);
        }
        else if(type == "location") {
            //add the new picture for the location type
            Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.generic_view);
            imgView.setImageBitmap(bitmap);
        }
        else if(type == "picture"){
            //add the new picture for the picture type
            Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.generic_view);
            imgView.setImageBitmap(bitmap);
        }
    }


    /*------------ setPollTitle -----------
          change the picture of the object
          This is invoked when a user opens the poll
    */
    public void setPollTitle(String title){
        this.pollTitle = title;
        TextView textView = (TextView)findViewById(R.id.poll_object_poll_title);
        textView.setText(this.pollTitle);
    }


    /*------------ getID -----------
          return id of the poll
    */
    public int getID() {
        return(this.id);
    }


    /*------------ getPollTitle -----------
          gives the poll title to whoever calls it
    */
    public String getPollTitle()
    {
        return(this.pollTitle);
    }


    /*------------ setAsPollUserCreated -----------
          changes the background so the user know they created it
    */
    public void setAsPollUserCreated() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.poll_object_main_layout);
        linearLayout.setBackgroundResource(R.drawable.poll_created_border);
    }


    //need a better name for this function
    /*------------ setAsPollUserWasGiven -----------
          changes the background so the user knows they didn't create
          it.  this normally won't be called since this is default behavior
    */
    public void setAsPollUserWasGiven() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.poll_object_main_layout);
        linearLayout.setBackgroundResource(R.drawable.poll_border);
    }
}
