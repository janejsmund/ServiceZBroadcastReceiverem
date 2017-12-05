package com.example.admin.servicezbroadcastreceiverem;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends Activity {

    public static final String myIntent = "com.example.admin.rozglaszaczintencji.MY_INTENT";
    private MyBroadcastReceiver mbr = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mbr, new IntentFilter(myIntent));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mbr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mbr, new IntentFilter(myIntent));
    }
}
