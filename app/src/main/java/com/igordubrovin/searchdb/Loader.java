package com.igordubrovin.searchdb;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;

/**
 * Created by Игорь on 17.02.2017.
 */

public class Loader extends CursorLoader {

    Bundle bundle;

    public Loader(Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
    }

    @Override
    protected Cursor onLoadInBackground()
    {
        DbOfStationsSearch db = new DbOfStationsSearch(getContext(), bundle.getString("upd"));
        Cursor cursor = db.getEmployees();
        return cursor;
    }
}
