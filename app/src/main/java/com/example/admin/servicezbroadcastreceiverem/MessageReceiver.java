package com.example.admin.servicezbroadcastreceiverem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
        }

        Intent updateUI = new Intent(DBContract.UPDATE_UI_FILTER);
        context.sendBroadcast(updateUI);

    }

}
