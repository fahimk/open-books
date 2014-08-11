package com.fahim.openbooks.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fahim.openbooks.R;
import com.fahim.openbooks.retrofit.Feed;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Fahim on 8/10/2014.
 */
public class BookListItemAdapter extends ArrayAdapter<Feed.Entry> {
    private Activity mActivity;

    public BookListItemAdapter(Activity activity, List<Feed.Entry> items) {
        super(activity, R.layout.row, R.id.title, items);
        mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = super.getView(position, convertView, parent);
        Feed.Entry entry = getItem(position);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        Picasso.with(mActivity).load(entry.map.get("http://opds-spec.org/image/thumbnail")).into(icon);

        TextView title = (TextView) row.findViewById(R.id.title);

        title.setText(Html.fromHtml(getItem(position).title));
        return (row);
    }
}
