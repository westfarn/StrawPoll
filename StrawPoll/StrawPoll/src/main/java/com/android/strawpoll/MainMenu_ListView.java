package com.android.strawpoll;

/**
 * Created by Joe on 1/6/14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.widget.Button;
import android.util.Log;

public class MainMenu_ListView extends Activity {
    ListView list;
    String[] questions = {
            "How many ho hos can Joe eat in one sitting?",
            "What does the fox say?",
            "Did Ryan just give Jake some nuclear codes?",
            "How many times will Jake say I love you while he's drunk?",
            "Do these shoes match?",
            "Where should we get drinks tonight?",
            "Is Need for Speed Rivals awesome?"
    } ;

    String[] username = {
            "getsilly13",
            "jdawwwwwwwkie",
            "westfarn",
            "bigtitties23",
            "bubba1973",
            "DaGrapist",
            "CoPKiLlA"
    };
    Integer[] imageId = {
            R.drawable.contact_book,
            R.drawable.contact_book,
            R.drawable.contact_book,
            R.drawable.contact_book,
            R.drawable.contact_book,
            R.drawable.contact_book,
            R.drawable.contact_book,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        MainMenu_CustomList adapter = new
                MainMenu_CustomList(MainMenu_ListView.this, questions, username, imageId);
        list=(ListView)findViewById(R.id.main_menu_list_view);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainMenu_ListView.this, "You Clicked at " +questions[+ position], Toast.LENGTH_SHORT).show();
            }
        });
    }

        /*@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main_menu, menu);
            return true;
        }*/


        public void goToSettings(View buttons) {
            Log.i("clicks", "You Clicked Settings");
            Intent i=new Intent(MainMenu_ListView.this, settings_menu.class);
            startActivity(i);
    }

        public void goToContacts(View buttons) {
            Log.i("clicks", "You Clicked Contacts");
            Intent i=new Intent(MainMenu_ListView.this, contacts_menu.class);
            startActivity(i);
    }

}



