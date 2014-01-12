package com.android.strawpoll;

/**
 * Created by Joe on 1/6/14.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;

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

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String SENDER_ID = "My-ID";
    static final String TAG = "STRAWPOLL";
    GoogleCloudMessaging gcm;
    Context context;
    String regid;

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

        //do the gcm stuff
        this.context = getApplicationContext();
        //check device for play services apk. if check succeeds, proceed with GCM registration
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);
            if(regid.isEmpty()) {
                registerInBackground();
            } else{
                Log.i(TAG,"No valid Google Play Servies APK found.");
            }
        }
    }

	
	    public void goToSettings(View buttons) {
            Log.i("clicks", "You Clicked Settings");
            Intent i=new Intent(MainMenu_ListView.this, SettingsMenu.class);
            startActivity(i);
    }

        public void goToContacts(View buttons) {
            Log.i("clicks", "You Clicked Contacts");
            Intent i=new Intent(MainMenu_ListView.this, ContactsMenu.class);
            startActivity(i);
    }
	
    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Service APK
        checkPlayServices();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "this device is not supported");
                finish();
            }
            return false;
        }
        return true;
    }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG,"Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences preferences = getGcmPreferences(context);
        String registrationId = preferences.getString(PROPERTY_REG_ID, "");
        if(registrationId.isEmpty()) {
            Log.i(TAG,"Registration not found.");
            return "";
        }

        //Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version
        int registeredVersion = preferences.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG," App version changed.");
            return "";
        }
        return registrationId;
    }

    /*
        Registers the application with GCM servers asynchronously,
        Stores the registration ID and the app versionCode in the application's
        shared preferences.

     */

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String message = "";
                try{
                    if(gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    message = "Device registerd, registration ID=" + regid;
                    // you should send the registration ID to your server over HTTP,so it
                    //can use GCM/HTTP or CCS to send messages to your app
                    sendRegistrationIdToBackend();
                     //for this demo; we don't need to send it because the device will send
                    //upstream messages to a server that echo back the message using th e
                    // 'from' address in the message

                    //persist the regID - no need to register again
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    message = "Error :" + ex.getMessage();
                    // if there is an error, don't just keep trying to register
                    // require the user to click a button again, or preform
                    //exponential back -off
                }
                return message;
            }

            @Override
            protected void onPostExecute(String msg) {
                //show some text
            }
        }.execute(null,null,null);
    }

    //Send an upstream message
    public void onClick(final View view) {
        //this was to do the background stuff
    }

    private static int getAppVersion(Context context) {
        try{
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private SharedPreferences getGcmPreferences(Context context) {
        //this sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you
        return getSharedPreferences(MainMenu_ListView.class.getSimpleName(),Context.MODE_PRIVATE);
    }

    //send the registration ID to you server over HTTP, so it can use GCM/HTTP or CSS to send
    //messages to your app.  Npt needed for this demo since the device sends upstream messages
    //to a server that echoes back the message using the 'from' address in the message
    private void sendRegistrationIdToBackend(){
        //your implementation here
    }

}
