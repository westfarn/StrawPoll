package com.android.strawpoll;

/**
 * Created by Joe on 1/7/14.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenu_CustomList extends ArrayAdapter<String>{

    private final Context context;
    private final String[] questions;
    private final String[] username;
    private final Integer[] imageId;


    public MainMenu_CustomList(Context context, String[] questions, String[] username, Integer[] imageId) {
        super(context, R.layout.main_menu_list, questions);
        this.context = context;
        this.questions = questions;
        this.username = username;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.main_menu_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.main_menu_question_text);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.main_menu_username);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.main_menu_profile_picture);
        txtTitle.setText(questions[position]);
        txtUsername.setText(username[position]);
        return rowView;
    }
}
