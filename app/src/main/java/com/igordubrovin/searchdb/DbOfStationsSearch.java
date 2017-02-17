package com.igordubrovin.searchdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Игорь on 16.02.2017.
 */

public class DbOfStationsSearch extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "DbTest.db";
    private static final String COLUMN_NAME_CITY = "Name";
    private static final int DATABASE_VERSION = 4;
    private String searchName;


    public DbOfStationsSearch(Context context, String searchName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.setForcedUpgrade(4);
        this.searchName = searchName;
    }


    public Cursor getEmployees() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String sqlTables = "Test";

        String[] args;

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, null, "(" + COLUMN_NAME_CITY + " LIKE '" + searchName + "%')", null,
                null, null, null);

        c.moveToFirst();
        db.close();
        return c;
    }
}