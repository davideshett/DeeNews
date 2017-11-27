package com.example.davidson.deenews.rest;

import com.example.davidson.deenews.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("v1/articles?")
    Call<NewsResponse> getTopNews(@Query("source") String source, @Query("sortBy") String sortBy,
                                  @Query("apiKey") String apikey);
}
