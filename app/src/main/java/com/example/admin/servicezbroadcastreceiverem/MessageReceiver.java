package com.example.admin.servicezbroadcastreceiverem;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 12/5/2017.
 */

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Custom message receiver", "Started receiving!");

        String message = intent.getStringExtra("message");

        if (message != null) {

            Log.i("Custom message receiver", "Started writing to db!");

            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db  = dbHelper.getWritableDatabase();

            dbHelper.saveMessage(message, db);
            dbHelper.close();

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "My channel");
            mBuilder.setContentTitle("New notification")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText(message);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, mBuilder.build());
        }

        Intent updateUI = new Intent(DBContract.UPDATE_UI_FILTER);
        context.sendBroadcast(updateUI);

    }

}
