package com.android.strawpoll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
import java.util.jar.Attributes;

/**
 * Created by Jacob on 1/6/14.
 */
public class PhotoGrid extends Activity {
    private static ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_grid);
        imageAdapter = new ImageAdapter(this);
        GridView gridView = (GridView) findViewById(R.id.photo_grid_main_layout);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(PhotoGrid.this,"" + position, Toast.LENGTH_SHORT).show();
                imageAdapter.selectImage(position);


                View v1 = imageAdapter.getView(position, v, parent);


            }

        });
    }


}

