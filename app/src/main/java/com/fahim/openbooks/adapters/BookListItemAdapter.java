package com.fahim.openbooks.adapters;

import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fahim.openbooks.retrofit.BookListItem;

import java.util.List;

/**
 * Created by Fahim on 8/10/2014.
 */
public class BookListItemAdapter extends ArrayAdapter<BookListItem> {
    public BookListItemAdapter(Activity activity, List<BookListItem> items) {
        super(activity, android.R.layout.simple_list_item_1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=super.getView(position, convertView, parent);
        TextView title=(TextView)row.findViewById(android.R.id.text1);
        if(getItem(position).toString().contains("Fables")) {
            Log.e("test", "test");
        }
        else if(getItem(position).toString().contains("Subjects")) {
            Log.e("test2", "test2");
        }
        title.setText(Html.fromHtml(getItem(position).toString()));
        return(row);
    }
}
