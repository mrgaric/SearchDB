package com.igordubrovin.searchdb;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Игорь on 18.02.2017.
 */

public class MyEditText extends EditText {

    private OnSearchInDBListener mOnSearchInDBListener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //при установке слушателя поиска данных, он будет срабатывать при изменении текста в editText
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
//        if (mOnSearchInDBListener != null){
//
//            mOnSearchInDBListener.onSearchInDB();
//        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            clearFocus();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setOnSearchInDBListener(@Nullable OnSearchInDBListener onSearchInDBListener){
        mOnSearchInDBListener = onSearchInDBListener;
    }

    public void setDataForSearch(Uri uriDB){

    }

    interface OnSearchInDBListener{
        void onSearchInDB();
    }

}
