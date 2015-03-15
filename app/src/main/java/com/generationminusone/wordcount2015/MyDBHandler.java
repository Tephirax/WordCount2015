package com.generationminusone.wordcount2015;

import com.generationminusone.wordcount2015.provider.MyContentProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    private ContentResolver myCR;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    public static final String TABLE_PROFILE = "profile";
    private static final String TAG = "MyDBHandler";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_RANKNUM = "ranknum";

    private static final String DATABASE_CREATE1 =
            "create table profile (_id integer primary key autoincrement, name text, ranknum integer);";

    public MyDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db); /* TODO Need to update to allow db updates without destroying data (ALTER statements?)*/
    }

    public void addProfile(Prof_Handler profile) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, profile.getName());
        values.put(KEY_RANKNUM, profile.getRanknum());

        myCR.insert(MyContentProvider.CONTENT_URI_PROFILE, values);

    }

    public Prof_Handler findProfile(String name) {
        String[] projection = { KEY_ID, KEY_NAME, KEY_RANKNUM };

        String selection =  KEY_NAME + " = \"" + name + "\"";

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI_PROFILE,
                projection, selection, null, null);

        Prof_Handler profile = new Prof_Handler();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            profile.setID(Integer.parseInt(cursor.getString(0)));
            profile.setName(cursor.getString(1));
            profile.setRanknum(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            profile = null;
        }
        return profile;
    }

    public boolean deleteProfile(String name) {
        boolean result = false;

        String selection = KEY_NAME + " = \"" + name + "\"";

        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI_PROFILE, selection, null);

        if (rowsDeleted > 0) {
            result = true;
        }

        return result;
    }
}