package com.xxun.xunscore.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.content.UriMatcher;
/**
 * Created by duanjinqian on 2020/1/9.
 */

public class ScoreProvider extends ContentProvider{
    private static final String TAG = "ScoreProvider";

    private static final String DATABASE_NAME = "XunScore.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_RECORD = "record";
    private static final String TABLE_NAME_TASK = "task";

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDatabaseHelper;

    public static final String AUTHORITY = "com.xiaoxun.score.provider";
    public static final Uri CONTENT_URI_RECORD = Uri.parse("content://" + AUTHORITY + "/record");
    public static final Uri CONTENT_URI_TASK = Uri.parse("content://" + AUTHORITY + "/task");
    private static UriMatcher uriMatcher;
    public static final int CODE_RECORD = 0;
    public static final int CODE_TASK = 1;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "record", CODE_RECORD);
        uriMatcher.addURI(AUTHORITY, "task", CODE_TASK);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private Context mContext;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table " + TABLE_NAME_TASK + "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "task_name TEXT,"
                    + "score_type INTEGER,"
                    + "task_action INTEGER,"
                    + "score_use_state INTEGER,"
                    + "task_get_score INTEGER,"
                    + "get_score_time TEXT,"
                    + "upload_flag  INTEGER,"
                    + "task_completed_num INTEGER,"
                    + "task_num);");
            db.execSQL("Create table " + TABLE_NAME_RECORD + "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "task_name TEXT,"
                    + "score_type INTEGER,"
                    + "task_action INTEGER,"
                    + "score_use_state INTEGER,"
                    + "task_get_score INTEGER,"
                    + "get_score_time TEXT,"
                    + "upload_flag  INTEGER,"
                    + "task_completed_num INTEGER,"
                    + "task_num);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME_TASK);
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME_RECORD);
            onCreate(db);
        }

    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri url, String selection, String[] selectionArgs) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        //getContext().getContentResolver().notifyChange(url, null, false);
        String table = null;
        switch (uriMatcher.match(url)) {
            case CODE_RECORD:
                table = TABLE_NAME_RECORD;
                break;
            case CODE_TASK:
                table = TABLE_NAME_TASK;
                break;
            default:
                break;
        }
        return mDatabase.delete(table, selection, selectionArgs);
    }

    @Override
    public String getType(Uri url) {
        return null;
    }

    @Override
    public Uri insert(Uri url, ContentValues initialValues) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues(initialValues);
        String table = null;
        switch (uriMatcher.match(url)) {
            case CODE_RECORD:
                table = TABLE_NAME_RECORD;
                break;
            case CODE_TASK:
                table = TABLE_NAME_TASK;
                break;
            default:
                break;
        }
        long rowId = mDatabase.insert(table, null, values);
        if (rowId > 0) {
            Uri rowUri = ContentUris.appendId(url.buildUpon(), rowId).build();
            getContext().getContentResolver().notifyChange(url, null, false);
            return rowUri;
        }
        throw new SQLException("duanjinqian: Failed to insert row into " + url);
    }

    @Override
    public Cursor query(Uri url, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        String table = null;
        switch (uriMatcher.match(url)) {
            case CODE_RECORD:
                table = TABLE_NAME_RECORD;
                break;
            case CODE_TASK:
                table = TABLE_NAME_TASK;
                break;
            default:
                break;
        }
        qb.setTables(table);

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), url);
        }
        return c;
    }

    @Override
    public int update(Uri url, ContentValues initialValues, String where, String[] whereArgs) {
        mDatabase = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues(initialValues);
        String table = null;
        switch (uriMatcher.match(url)) {
            case CODE_RECORD:
                table = TABLE_NAME_RECORD;
                break;
            case CODE_TASK:
                table = TABLE_NAME_TASK;
                break;
            default:
                break;
        }
        return mDatabase.update(table,values,where,whereArgs);
    }
}