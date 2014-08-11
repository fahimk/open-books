package com.fahim.openbooks.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fahim.openbooks.R;
import com.fahim.openbooks.fragments.BookDetailFragment;
import com.fahim.openbooks.fragments.TopBooksFragment;
import com.fahim.openbooks.retrofit.BookListItem;


public class MainActivity extends Activity implements TopBooksFragment.Contract {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.detail_fragment) != null) {
            mTwoPane = true;
            getFragmentManager().beginTransaction().add(R.id.detail_fragment, new BookDetailFragment()).commit();
        }

        TopBooksFragment topBooksFragment = (TopBooksFragment) getFragmentManager().findFragmentById(R.id.main_fragment);
        if(topBooksFragment == null) {
            topBooksFragment = new TopBooksFragment();
            getFragmentManager().beginTransaction().add(R.id.main_fragment, topBooksFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showItem(BookListItem item) {

    }
}
