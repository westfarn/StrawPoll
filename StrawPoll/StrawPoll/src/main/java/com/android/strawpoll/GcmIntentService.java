package com.android.strawpoll;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.app.IntentService;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by westf_000 on 1/11/14.
 */
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService(){
        super("GcmIntentService");
    }

    public static final String TAG = "GCM DEMO";

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if(!extras.isEmpty()) { // has effect of unparcelling Bundle
            /*
              Filter messages based on message type.  Since it is likely that GCM will be
              extended in the future with new message types, just ignore any message types your're
              not interested in, or that you don't recognize,
             */

            if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // this is a regular message where we want to do some type of work
                // this loop represents the service doing some work
                for (int i = 0; i < 5; i++ ){
                    Log.i(TAG, "Working..." + (i + 1) + "/5 @" + SystemClock.elapsedRealtime());
                    try{
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        //catch the error
                    }
                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                sendNotification("Received: " + extras.toString());
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        //Release the wak lock provided by the WakefulBroadcastReveiver
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // this is just one simple example of what you might choose to do with
    // a gcm message
    private void sendNotification(String message) {
        notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = null;
         //contentIntent = PendingIntent.getActivities(this, 0, new Intent(this, MainMenu_ListView.class), 0); //<-- need to change this

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.generic_new)
                .setContentTitle("StrawPoll")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message);
        builder.setContentIntent(contentIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
