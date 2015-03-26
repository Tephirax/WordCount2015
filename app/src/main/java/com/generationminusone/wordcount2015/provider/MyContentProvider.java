package com.generationminusone.wordcount2015.provider;

import com.generationminusone.wordcount2015.MyDBHandler;

import android.content.ContentProvider;
import android.content.ContentUris;
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
    private static final String TABLE_PROFILES = "profiles";
    private static final String TABLE_PROJECTS = "projects";
    private static final String TABLE_ACHIEVEMENTS = "achievements";
    public static final Uri CONTENT_URI_PROFILES = Uri.parse("content://" + AUTHORITY + "/" + TABLE_PROFILES);
    public static final Uri CONTENT_URI_PROJECTS = Uri.parse("content://" + AUTHORITY + "/" + TABLE_PROJECTS);
    public static final Uri CONTENT_URI_ACHIEVEMENTS = Uri.parse("content://" + AUTHORITY + "/" + TABLE_ACHIEVEMENTS);
    // Create integers for use in UriMatcher;
    public static final int PROFILES = 1;
    public static final int PROFILE_ID = 2;
    public static final int PROJECTS = 11;
    public static final int PROJECT_ID = 12;
    public static final int ACHIEVEMENTS = 21;
    public static final int ACHIEVEMENT_ID = 22;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // Profiles
        sURIMatcher.addURI(AUTHORITY, TABLE_PROFILES, PROFILES);
        sURIMatcher.addURI(AUTHORITY, TABLE_PROFILES + "/#", PROFILE_ID);
        // Projects
        sURIMatcher.addURI(AUTHORITY, TABLE_PROJECTS, PROJECTS);
        sURIMatcher.addURI(AUTHORITY, TABLE_PROJECTS + "/#", PROJECT_ID);
        // Achievements
        sURIMatcher.addURI(AUTHORITY, TABLE_ACHIEVEMENTS, ACHIEVEMENTS);
        sURIMatcher.addURI(AUTHORITY, TABLE_ACHIEVEMENTS + "/#", ACHIEVEMENT_ID);
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
        queryBuilder.setTables(MyDBHandler.TABLE_PROFILES);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case PROFILES:
                break;
            case PROFILE_ID:
                queryBuilder.appendWhere(MyDBHandler.KEY_ROWIDPROF + "=" + uri.getLastPathSegment());
                break;
            case PROJECTS:
                break;
            case PROJECT_ID:
                queryBuilder.appendWhere(MyDBHandler.KEY_ROWIDPROJ + "=" + uri.getLastPathSegment());
                break;
            case ACHIEVEMENTS:
                break;
            case ACHIEVEMENT_ID:
                queryBuilder.appendWhere(MyDBHandler.KEY_ROWIDACH + "=" + uri.getLastPathSegment());
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

        long id;
        Uri _uri = null;

        switch (uriType) {
            case PROFILES:
                id = sqlDB.insert(MyDBHandler.TABLE_PROFILES, null, values);
                //---if added successfully---//
                if (id > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_PROFILES, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case PROJECTS:
                id = sqlDB.insert(MyDBHandler.TABLE_PROJECTS, null, values);
                //---if added successfully---//
                if (id > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_PROJECTS, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case ACHIEVEMENTS:
                id = sqlDB.insert(MyDBHandler.TABLE_ACHIEVEMENTS, null, values);
                //---if added successfully---//
                if (id > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_ACHIEVEMENTS, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return _uri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsDeleted;
        String id;

        switch (uriType) {
            case PROFILES:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILES,
                        selection,
                        selectionArgs);
                break;
            case PROFILE_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILES,
                            MyDBHandler.KEY_ROWIDPROF + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROFILES,
                            MyDBHandler.KEY_ROWIDPROF + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            case PROJECTS:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROJECTS,
                        selection,
                        selectionArgs);
                break;
            case PROJECT_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROJECTS,
                            MyDBHandler.KEY_ROWIDPROJ + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PROJECTS,
                            MyDBHandler.KEY_ROWIDPROJ + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            case ACHIEVEMENTS:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_ACHIEVEMENTS,
                        selection,
                        selectionArgs);
                break;
            case ACHIEVEMENT_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_ACHIEVEMENTS,
                         MyDBHandler.KEY_ROWIDACH + "=" + id,
                         null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_ACHIEVEMENTS,
                                  MyDBHandler.KEY_ROWIDACH + "=" + id + " and " + selection, selectionArgs);
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
        int rowsUpdated;
        String id;

        switch (uriType) {
            case PROFILES:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PROFILES,
                         values,
                         selection,
                         selectionArgs);
                break;
            case PROFILE_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROFILES,
                              values,
                              MyDBHandler.KEY_ROWIDPROF + "=" + id,
                              null);
                } else {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROFILES,
                               values,
                               MyDBHandler.KEY_ROWIDPROF + "=" + id + " and " + selection,
                               selectionArgs);
                }
                break;
            case PROJECTS:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PROJECTS,
                          values,
                          selection,
                          selectionArgs);
                break;
            case PROJECT_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROJECTS,
                               values,
                               MyDBHandler.KEY_ROWIDPROJ + "=" + id,
                               null);
                } else {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_PROJECTS,
                               values,
                               MyDBHandler.KEY_ROWIDPROJ + "=" + id + " and " + selection,
                               selectionArgs);
                }
                break;
            case ACHIEVEMENTS:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_ACHIEVEMENTS,
                          values,
                          selection,
                          selectionArgs);
                break;
            case ACHIEVEMENT_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_ACHIEVEMENTS,
                               values,
                               MyDBHandler.KEY_ROWIDACH + "=" + id,
                               null);
                } else {
                    rowsUpdated =   sqlDB.update(MyDBHandler.TABLE_ACHIEVEMENTS,
                               values,
                               MyDBHandler.KEY_ROWIDACH + "=" + id + " and " + selection,
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
