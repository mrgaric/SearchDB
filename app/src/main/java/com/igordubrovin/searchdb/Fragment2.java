package com.igordubrovin.searchdb;

import android.content.*;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.*;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by Игорь on 19.02.2017.
 */

public class Fragment2 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    RecyclerView rvDatabase;
    LinearLayoutManager linearLayoutManager;
    ScaleInAnimationAdapter scaleInAnimationAdapter;
    Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_recycler, null);
        adapter = new Adapter(getContext());
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        scaleInAnimationAdapter.setDuration(500);
        scaleInAnimationAdapter.setFirstOnly(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvDatabase = (RecyclerView)v.findViewById(R.id.rvDatabase);
        rvDatabase.setLayoutManager(linearLayoutManager);
        rvDatabase.setAdapter(scaleInAnimationAdapter);
        return v;
    }

    public void change(Bundle bundle){
        getActivity().getSupportLoaderManager().restartLoader(0, bundle, Fragment2.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new com.igordubrovin.searchdb.Loader(getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
