package com.example.admin.servicezbroadcastreceiverem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 12/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messageDB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE = "create table " + DBContract.TABLE_NAME +
            "(id integer primary key autoincrement," + DBContract.INCOMING_MESSAGE +" text);";
    private static final String DROP_TABLE = "drop table if exists " + DBContract.TABLE_NAME;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void saveMessage(String message, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.INCOMING_MESSAGE, message);

        db.insert(DBContract.TABLE_NAME, null, contentValues);
    }

    public Cursor readMessage(SQLiteDatabase db) {

        String[] projections = {"id", DBContract.INCOMING_MESSAGE};

        return (db.query(DBContract.TABLE_NAME, projections, null, null, null, null, null));
    }
}
