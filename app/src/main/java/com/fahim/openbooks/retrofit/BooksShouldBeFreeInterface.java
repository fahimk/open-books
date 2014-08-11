package com.fahim.openbooks.retrofit;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Fahim on 8/10/2014.
 */
public interface BooksShouldBeFreeInterface {
    @GET("/top.atom?range=month")
    void topBooks(Callback<Feed> callBack);
}
