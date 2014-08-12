package com.fahim.openbooks.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahim.openbooks.R;
import com.fahim.openbooks.retrofit.Feed;

/**
 * Created by Fahim on 8/10/2014.
 */
public class BookDetailFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        if(getArguments() != null) {
            Feed.Entry entry = (Feed.Entry) getArguments().getSerializable("Feed.Entry");

            TextView summary = (TextView) view.findViewById(R.id.detail_summary);
            summary.setText(entry.summary);
        }
        return view;
    }
}
