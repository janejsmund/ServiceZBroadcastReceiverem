package com.example.admin.servicezbroadcastreceiverem;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static final String myIntent = "com.MY_INTENT";
    private MessageReceiver mbr = new MessageReceiver();

    private RecyclerView recyclerView;
    private TextView emptyTV;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<IncomingMessage> arrayList = new ArrayList<>();
    private RecyclerAdapter adapter;

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        emptyTV = findViewById(R.id.emptyTV);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        readFromDb();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                readFromDb();
            }
        };
    }

    private void readFromDb() {

        arrayList.clear();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.readMessage(database);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String message;
                int id;

                message = cursor.getString(cursor.getColumnIndex(DBContract.INCOMING_MESSAGE));
                id = cursor.getInt(cursor.getColumnIndex("id"));

                arrayList.add(new IncomingMessage(id, message));
            }
            cursor.close();
            dbHelper.close();
            adapter.notifyDataSetChanged();

            recyclerView.setVisibility(View.VISIBLE);
            emptyTV.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mbr, new IntentFilter(myIntent));
        registerReceiver(broadcastReceiver, new IntentFilter(DBContract.UPDATE_UI_FILTER));

        Log.i("Custom broadcast receiver", "Started!");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Custom broadcast receiver", "Paused!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mbr, new IntentFilter(myIntent));
        registerReceiver(broadcastReceiver, new IntentFilter(DBContract.UPDATE_UI_FILTER));

        Log.i("Custom broadcast receiver", "Resumed!");
    }
}
