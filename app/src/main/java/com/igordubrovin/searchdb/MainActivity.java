package com.igordubrovin.searchdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    RecyclerView rvDatabase;
    EditText etSearch;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    ScaleInAnimationAdapter scaleInAnimationAdapter;

    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DbOfStationsSearch db = new DbOfStationsSearch(this);
//        cursor = db.getEmployees();

        adapter = new Adapter(this);
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        scaleInAnimationAdapter.setDuration(500);
        scaleInAnimationAdapter.setFirstOnly(false);

        linearLayoutManager = new LinearLayoutManager(this);
        rvDatabase = (RecyclerView)findViewById(R.id.rvDatabase);
        rvDatabase.setLayoutManager(linearLayoutManager);
        rvDatabase.setAdapter(scaleInAnimationAdapter);

        etSearch = (EditText)findViewById(R.id.etSearch);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Bundle bundle = new Bundle();
                bundle.putString("upd", s.toString());
                getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnTest = (Button)findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getItemCount();
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new com.igordubrovin.searchdb.Loader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
