package com.igordubrovin.searchdb;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Игорь on 17.02.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderRv>{

    Context context;
    Cursor cursor = null;

    Adapter(Context context){
        this.context = context;
    }

    public void swapCursor(Cursor cursor){
        this.cursor = cursor;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolderRv onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolderRv(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRv holder, int position) {
        cursor.moveToPosition(position);
        holder.tvItem.setText(cursor.getString(cursor.getColumnIndex("Name")));
    }

    @Override
    public int getItemCount() {
        if (cursor != null)
        return cursor.getCount();
        else return 0;
    }

    class ViewHolderRv extends RecyclerView.ViewHolder {
        TextView tvItem;
        public ViewHolderRv(View itemView) {
            super(itemView);
            tvItem = (TextView)itemView.findViewById(R.id.tvItem);
        }
    }
}
