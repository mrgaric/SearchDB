package com.igordubrovin.searchdb;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity{

    Fragment1 fragment1;
    Fragment2 fragment2;
    MyEditText etSearch;



    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DbOfStationsSearch db = new DbOfStationsSearch(this);
//        cursor = db.getEmployees();

        fragment2 = new Fragment2();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentCont, fragment2);
        transaction.commit();

        etSearch = (MyEditText) findViewById(R.id.etSearch);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Bundle bundle = new Bundle();
                bundle.putString("upd", s.toString());
                fragment2.change(bundle);
//                getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        btnTest = (Button)findViewById(R.id.btnTest);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnTest.startAnimation(anim);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_close_exit);
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etSearch.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
////        return new com.igordubrovin.searchdb.Loader(this, args);
//        return new android.support.v4.content.CursorLoader(
//                this,
//                Uri.parse("content://com.igordubrovin.searchdb/Test"),
//                null,
//                args.getString("upd"),
//                null,
//                null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
}
