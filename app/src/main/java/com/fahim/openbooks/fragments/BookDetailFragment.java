package com.fahim.openbooks.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fahim.openbooks.R;

/**
 * Created by Fahim on 8/10/2014.
 */
public class BookDetailFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_books, container, false);
        return view;
    }
}
