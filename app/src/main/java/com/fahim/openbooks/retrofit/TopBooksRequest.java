package com.fahim.openbooks.retrofit;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by Fahim on 8/28/2014.
 */
public class TopBooksRequest extends SpringAndroidSpiceRequest<Feed> {

    public TopBooksRequest() {
        super(Feed.class);
    }

    @Override
    public Feed loadDataFromNetwork() throws Exception {
        String url = String.format("http://www.feedbooks.com/books/top.atom?range=month");

        return getRestTemplate().getForObject(url, Feed.class);
    }
}
