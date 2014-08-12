package com.fahim.openbooks.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.fahim.openbooks.R;
import com.fahim.openbooks.fragments.BookDetailFragment;
import com.fahim.openbooks.retrofit.Feed;

/**
 * Created by Fahim on 8/11/2014.
 */
public class BookDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Feed.Entry entry = (Feed.Entry) getIntent().getSerializableExtra("Feed.Entry");

        FragmentManager fragmentManager = getFragmentManager();
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle bundle = new Bundle();
        //TODO: pass bundle from previous activity intent call
        bundle.putSerializable("Feed.Entry", entry);
        bookDetailFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.detail_fragment_container, bookDetailFragment).commit();
    }
}
