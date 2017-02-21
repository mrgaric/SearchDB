package com.igordubrovin.searchdb;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

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
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentCont, fragment1, "fragment1");
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
                /*Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_close_exit);*/
                android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentCont);
                if (fragment.getTag().equals("fragment1")){
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_close_exit, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter)
                            .replace(R.id.fragmentCont, fragment2, "fragment2")
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentCont, fragment1, "fragment1")
                            .commit();
                }

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
