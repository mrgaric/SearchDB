package com.igordubrovin.searchdb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Игорь on 19.02.2017.
 */

public class MyContentProvider extends ContentProvider {

    //db
    private static final String DATABASE_NAME = "DbTest.db";
    private static final String COLUMN_NAME_CITY = "Name";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "Test";

    //uri
    public static final String AUTHORITY = "com.igordubrovin.searchdb";
    private static final String SCHEME = "content://";
    private static final String PATH = "/Test";
    public static final Uri URI_CONNECT_DB = Uri.parse(SCHEME + AUTHORITY + PATH);

    static final int URI_TABLE = 1;

    // Типы данных
    // набор строк
    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PATH;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH, URI_TABLE);
    }

    DbOfStationsSearch dbOfStationsSearch;

    @Override
    public boolean onCreate() {
        dbOfStationsSearch = new DbOfStationsSearch(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)){
            case URI_TABLE:
                qb.setTables(TABLE);
                break;
            default: throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        Cursor c;
        SQLiteDatabase db = dbOfStationsSearch.getReadableDatabase();
        c = qb.query(db, null, "(" + COLUMN_NAME_CITY + " LIKE '" + selection + "%')", null,
                null, null, null);
        c.moveToFirst();
        int i = c.getCount();
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case URI_TABLE:
                return CONTENT_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private class DbOfStationsSearch extends SQLiteAssetHelper {



        public DbOfStationsSearch(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    }

}
