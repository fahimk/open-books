package com.fahim.openbooks.retrofit;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Fahim on 8/10/2014.
 */
public interface BooksShouldBeFreeInterface {
    @GET("/books.html")
    void topBooks(Callback<TopBooksList> callBack);
}
