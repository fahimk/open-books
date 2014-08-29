package com.fahim.openbooks.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fahim.openbooks.R;
import com.fahim.openbooks.adapters.BookListItemAdapter;
import com.fahim.openbooks.retrofit.Feed;
import com.fahim.openbooks.retrofit.TopBooksRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.PendingRequestListener;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.HListView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopBooksFragment extends Fragment implements it.sephiroth.android.library.widget.AdapterView.OnItemClickListener {

    private SpiceManager spiceManager = new SpiceManager(XmlSpringAndroidSpiceService.class);

    private HListView mListView;
    private BookListItemAdapter mBookListItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBookListItemAdapter = new BookListItemAdapter(getActivity(), new ArrayList<Feed.Entry>());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_books, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (HListView) view.findViewById(R.id.top_books_list);
        mListView.setOnItemClickListener(this);

        mListView.setAdapter(mBookListItemAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if(!spiceManager.isStarted()) {
            spiceManager.start(getActivity());
        }
        if (mBookListItemAdapter.getCount() == 0) {
            spiceManager.addListenerIfPending(Feed.class, "cachekey", new TopBooksRequestListener());
        }
    }

    @Override
    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> adapterView, View view, int position, long id) {
        BookListItemAdapter bookListItemAdapter = (BookListItemAdapter) mListView.getAdapter();
        ((Contract)getActivity()).showItem(bookListItemAdapter.getItem(position));
    }

    public interface Contract {
        void showItem(Feed.Entry item);
    }

    public TopBooksFragment() {
        // Required empty public constructor
    }

    private final class TopBooksRequestListener implements PendingRequestListener<Feed> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Error: " + spiceException.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Feed result) {
            mBookListItemAdapter.clear();
            mBookListItemAdapter.addAll(result.list);
        }

        @Override
        public void onRequestNotFound() {
            TopBooksRequest request = new TopBooksRequest();
            spiceManager.execute(request, "cachekey",
                    DurationInMillis.ALWAYS_EXPIRED, new TopBooksRequestListener());
        }
    }
}
