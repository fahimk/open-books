package com.fahim.openbooks.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fahim.openbooks.R;
import com.fahim.openbooks.adapters.BookListItemAdapter;
import com.fahim.openbooks.retrofit.BookListItem;
import com.fahim.openbooks.retrofit.BooksShouldBeFreeInterface;
import com.fahim.openbooks.retrofit.TopBooksList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopBooksFragment extends Fragment implements Callback<TopBooksList>, AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookListItemAdapter bookListItemAdapter = (BookListItemAdapter) mListView.getAdapter();
        ((TopBooksFragment.Contract)getActivity()).showItem(bookListItemAdapter.getItem(position));
    }

    public interface Contract {
        void showItem(BookListItem item);
    }

    private ListView mListView;

    public TopBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_books, container, false);
        mListView = (ListView) view.findViewById(R.id.top_books_list);
        mListView.setOnItemClickListener(this);
        setRetainInstance(true);

        //Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter =
                new RestAdapter.Builder().setEndpoint("http://openbooks.comule.com") //.setConverter(new GsonConverter(gson))
                        .build();

        BooksShouldBeFreeInterface booksShouldBeFreeInterface = restAdapter.create(BooksShouldBeFreeInterface.class);
        booksShouldBeFreeInterface.topBooks(this);

        return view;
    }


    @Override
    public void success(TopBooksList topBooksList, Response response) {
        if(mListView != null) {
            mListView.setAdapter(new BookListItemAdapter(getActivity(), topBooksList.top));
        }
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        Toast.makeText(getActivity(), retrofitError.getMessage(),
        Toast.LENGTH_LONG).show();
        Log.e("TopBooksFragment",
                "Exception from Retrofit request to TopBooksList", retrofitError);
    }
}