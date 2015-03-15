package com.generationminusone.wordcount2015.provider;

import com.generationminusone.wordcount2015.MyDBHandler;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {
    /* Handler for database tables:
     * profile table: stores information on each profile in a single line. Fields are:
     * _id, rank, totalxp, xptonextrank, name, sex.
     * projects table: stores information on each project in a single line. Fields are:
     * _id, title, type, wordcount, total, datestart, datecomplete,
     * achievements table:
     * _id, profile, name, achieved, dateachieved
     * */

    private MyDBHandler myDB;

    public MyContentProvider() {

    }

    // Expose database name/location
    public static final String AUTHORITY = "com.generationminusone.wordcount2015.provider.MyContentProvider";
    private static final String PROFILE_TABLE = "profile";
    public static final Uri CONTENT_URI_PROFILE = Uri.parse("content://" + AUTHORITY + "/" + PROFILE_TABLE);

    // Create integers for use in UriMatcher;
    public static final int PROFILES = 1;
    public static final int PROFILE_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PROFILE_TABLE, PROFILES);
        sURIMatcher.addURI(AUTHORITY, PROFILE_TABLE + "/#", PROFILE_ID);
    }
    // UriMatcher will now return the value of PROFILES (ie. 1) when just the products table is referenced in a URI,
    // and PROFILE_ID (ie. 2) when the URI includes the ID of a specific row in the table.

    @Override
    public boolean onCreate() {
        myDB = new MyDBHandler(getContext(), null, null, 1);
        return false;
    }

    // projection = String of variable names to be included
    // selection = where clause of SQL query
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDBHandler.TABLE_PROFILE);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case PROFILES:
                break;
            case PROFILE_ID:
                queryBuilder.appendWhere(MyDBHandler.KEY_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(myDB.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case PROFILES:
                id = sqlDB.insert(MyDBHandler.TABLE_PROFILE, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(PROFILE_TABLE + "/" + id);
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case PROFILES:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILE,
                        selection,
                        selectionArgs);
                break;
            case PROFILE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILE,
                            MyDBHandler.KEY_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILE,
                            MyDBHandler.KEY_ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case PROFILES:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PROFILE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case PROFILE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROFILE,
                                    values,
                                    MyDBHandler.KEY_ID + "=" + id,
                                    null);
                } else {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROFILE,
                                    values,
                                    MyDBHandler.KEY_ID + "=" + id + " and " + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

}
