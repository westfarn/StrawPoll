package com.android.strawpoll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Jacob on 1/6/14.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;


    public ImageAdapter(Context c){
        mContext = c;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0 ;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
            imageView.setSelected(false);

        } else {
            imageView =(ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to images
    private Integer[] mThumbIds = {
            R.drawable.generic_new, R.drawable.generic_view,
            R.drawable.generic_view,R.drawable.generic_new,
            R.drawable.generic_new, R.drawable.generic_view,
            R.drawable.generic_view,R.drawable.generic_new,
            R.drawable.generic_new, R.drawable.generic_view,
            R.drawable.generic_view,R.drawable.generic_new,
    };

    private boolean[] triggers = {
            false,false,
            false,false,
            false,false,
            false,false,
            false,false,
            false,false,

    };

    public void selectImage(int i){
        this.triggers[i] = !this.triggers[i];

        setTheBackground(i);
    }

    private void setTheBackground (int i){
        if (this.triggers[i]){
            mThumbIds[i] = R.drawable.generic_view;
        }else{
            mThumbIds[i] = R.drawable.generic_new;
        }
    }

}


