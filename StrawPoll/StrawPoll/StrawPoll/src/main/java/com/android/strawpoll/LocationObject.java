package com.android.strawpoll;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by westf_000 on 12/30/13.
 * This file is the LocationObject functionality
 * ToDo:
 *  1. allow a person to open up google maps from here
 *  2. get all relevant information
 */

public class LocationObject extends LinearLayout {

    //public members
    Location location;
    Address address;

    public LocationObject(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.location_object, this, true);

        //do extra stuff here

    }

    /*------------ LocationObject -----------
          a generic constructor for the LocationObject
    */
    public LocationObject(Context context) {
        this(context, null);
    }

    /*------------ setLocation -----------
          sets the location of the object
    */
    public void setLocation(Location location) {
        this.location = location;
    }

    /*------------ getLocation -----------
          gives the location of the object
    */
    public Location getLocation() {
        return this.location;
    }

    /*------------ setAddress -----------
          sets the address for the object
    */
    public void setAddress(Address address) {
        this.address = address;
    }

    /*------------ getAddress -----------
          gives the address to the view
    */
    public Address getAddress() {
        return this.address;
    }

}
