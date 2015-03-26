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
    public static final String TABLE_PROFILES = "profiles";
    public static final String TABLE_PROJECTS = "projects";
    public static final String TABLE_ACHIEVEMENTS = "achievements";
    private static final String TAG = "MyDBHandler";
    /* profiles table*/
    public static final String KEY_ROWIDPROF = "_id";
    public static final String KEY_PROFILENAME = "name";
    public static final String KEY_RANK = "rank";
    public static final String KEY_RANKNUM = "ranknum";
    public static final String KEY_TOTALXP = "totalxp";
    public static final String KEY_TOTXPTONEXT = "totxptonext";
    public static final String KEY_XPTONEXTRANK = "xptonextrank";
    public static final String KEY_XPLEVELFLOOR = "xplevelfloor";
    public static final String KEY_XPTARGET = "xptarget";
    public static final String KEY_TOTALWORDS = "totalwords";
    public static final String KEY_NUMOFPOSTS = "numofposts";
    public static final String KEY_NUMCONSEC = "numconsec";
    public static final String KEY_WORDCOUNTTARGET = "wctarget";
    public static final String KEY_EDITCOUNTTARGET = "edtarget";
    public static final String KEY_EDITUNIT = "edunit";
    public static final String KEY_ACTIVE = "active";
    public static final String KEY_LASTPOSTED = "lastposted";
    public static final String KEY_MULTIPLIER = "multiplier";
    public static final String KEY_DAILYWC = "dailywc";
    public static final String KEY_DAILYWCREM = "dailywcrem";
    /* projects table */
    public static final String KEY_ROWIDPROJ = "_id";
    public static final String KEY_PROFILE = "profile";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TYPE = "type";
    public static final String KEY_WORDCOUNT = "wordcount";
    public static final String KEY_TOTAL = "total";
    public static final String KEY_DATESTART = "datestart";
    public static final String KEY_DATECOMPLETE = "datecomplete";
    public static final String KEY_COMPFLAG = "compflag";
    public static final String KEY_ACTIVEPROJ = "activeproj";
    /* achievements table */
    public static final String KEY_ROWIDACH = "_id";
    public static final String KEY_ACHID = "achid";
    public static final String KEY_PROFILEACH = "profile";
    public static final String KEY_NAMEACH = "name";
    public static final String KEY_DESCACH = "description";
    public static final String KEY_ACHIEVED = "achieved";
    public static final String KEY_DATEACHIEVED = "dateachieved";

    private static final String DATABASE_CREATE_PROF =
            "create table " + TABLE_PROFILES + " (_id integer primary key autoincrement, name text, rank text, ranknum integer, totalxp integer,"
                    + "totxptonext integer, xptonextrank integer, xplevelfloor integer, xptarget integer, numofposts integer, "
                    + "numconsec integer, totalwords integer, sex text, wctarget integer, edtarget integer, edunit integer"
                    + "active integer, lastposted text, multiplier real, dailywc integer, dailywcrem integer);";
    private static final String DATABASE_CREATE_PROJ =
            "create table " + TABLE_PROJECTS + " (_id integer primary key autoincrement, profile integer,"
                    + "title text, type text, wordcount integer, total integer, datestart text" + ", datecomplete text, "
                    + "compflag integer, activeproj integer" + ");";
    private static final String DATABASE_CREATE_ACH =
            "create table " + TABLE_ACHIEVEMENTS + " (_id integer primary key autoincrement, achid integer, profile integer,"
                    + "name text, description text, achieved integer, dateachieved text);";

    public MyDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PROF);
        db.execSQL(DATABASE_CREATE_PROF);
        db.execSQL(DATABASE_CREATE_ACH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENTS);
        onCreate(db); /* TODO Need to update to allow db updates without destroying data (ALTER statements?)*/
    }

    public Cursor fetchAllProfiles(){
        String[] projection = { KEY_ROWIDPROF, KEY_PROFILENAME, KEY_RANK, KEY_RANKNUM, KEY_TOTALXP, KEY_TOTXPTONEXT,
                                KEY_XPTONEXTRANK, KEY_XPLEVELFLOOR, KEY_XPTARGET, KEY_TOTALWORDS, KEY_NUMOFPOSTS,
                                KEY_NUMCONSEC, KEY_WORDCOUNTTARGET, KEY_EDITCOUNTTARGET, KEY_EDITUNIT, KEY_ACTIVE,
                                KEY_LASTPOSTED, KEY_MULTIPLIER, KEY_DAILYWC, KEY_DAILYWCREM };
        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI_PROFILES,
                projection, null, null, null);
        return cursor;
    }

    public void addProfile(Prof_Handler profile) {
        // TODO: Need to set up methods for other variables in Prof_Handler object class
        ContentValues values = new ContentValues();
        values.put(KEY_PROFILENAME, profile.getName());
        values.put(KEY_RANK, profile.getRank());
        values.put(KEY_RANKNUM, profile.getRanknum());
        values.put(KEY_TOTALXP, profile.getTotalXP());
        values.put(KEY_TOTXPTONEXT, profile.getTotXPToNext());
        values.put(KEY_XPTONEXTRANK, profile.getXPToNextRank());
        values.put(KEY_XPLEVELFLOOR, profile.getXPLevelFloor());
        values.put(KEY_XPTARGET, profile.getXPTarget());
        values.put(KEY_TOTALWORDS, profile.getTotalWords());
        values.put(KEY_NUMOFPOSTS, profile.getNumOfPosts());
        values.put(KEY_NUMCONSEC, profile.getNumConsec());
        values.put(KEY_WORDCOUNTTARGET, profile.getWordCountTarget());
        values.put(KEY_EDITCOUNTTARGET, profile.getEditCountTarget());
        values.put(KEY_EDITUNIT, profile.getEditUnit());
        values.put(KEY_ACTIVE, profile.getActive());
        values.put(KEY_LASTPOSTED, profile.getLastPosted());
        values.put(KEY_MULTIPLIER, profile.getMultiplier());
        values.put(KEY_DAILYWC, profile.getDailyWC());
        values.put(KEY_DAILYWCREM, profile.getDailyWCRem());
        myCR.insert(MyContentProvider.CONTENT_URI_PROFILES, values);

    }

    public Prof_Handler fetchProfile(Long id) {
        // SELECT Variables
        String[] projection = { KEY_ROWIDPROF, KEY_PROFILENAME, KEY_RANK, KEY_RANKNUM, KEY_TOTALXP, KEY_TOTXPTONEXT,
                                KEY_XPTONEXTRANK, KEY_XPLEVELFLOOR, KEY_XPTARGET, KEY_TOTALWORDS, KEY_NUMOFPOSTS,
                                KEY_NUMCONSEC, KEY_WORDCOUNTTARGET, KEY_EDITCOUNTTARGET, KEY_EDITUNIT, KEY_ACTIVE,
                                KEY_LASTPOSTED, KEY_MULTIPLIER, KEY_DAILYWC, KEY_DAILYWCREM };
        // WHERE statement
        String selection = KEY_ROWIDPROF + " = \"" + String.valueOf(id) + "\"";

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI_PROFILES,
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

    public Prof_Handler findProfile(String name) {
        String[] projection = { KEY_ROWIDPROF, KEY_PROFILENAME, KEY_RANKNUM };

        String selection =  KEY_PROFILENAME + " = \"" + name + "\"";

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI_PROFILES,
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

        String selection = KEY_PROFILENAME + " = \"" + name + "\"";

        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI_PROFILES, selection, null);

        if (rowsDeleted > 0) {
            result = true;
        }

        return result;
    }
}