package com.example.davidson.deenews.fragments;



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.davidson.deenews.R;
import com.example.davidson.deenews.adapter.RecyclerAdapter;
import com.example.davidson.deenews.model.News;
import com.example.davidson.deenews.model.NewsResponse;
import com.example.davidson.deenews.rest.ApiClient;
import com.example.davidson.deenews.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cnn extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener{

    private final static String apiKey = ""; //your api key
    List<News> news;
    String source = "cnn";
    String sortBy = "top";
    RecyclerAdapter mAdapter;
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cnn, null);
        recyclerView = view.findViewById(R.id.news_recycler_view);
         mAdapter = new RecyclerAdapter(news,recyclerView.getContext());

        loadCnnNews();
        setHasOptionsMenu(true);
        swipeRefreshLayout = view.findViewById(R.id.swipe_view);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (apiKey.isEmpty()){
            Toast.makeText(getContext(),"Obtain apikey from newsapi.org",Toast.LENGTH_LONG).show();

        }

         return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }


    private void loadCnnNews(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<NewsResponse> call = apiService.getTopNews(source,sortBy,apiKey);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                news = response.body().getArticles();
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new RecyclerAdapter(news,recyclerView.getContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(),"unable to connect!!",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onRefresh() {
        loadCnnNews();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<News> newsList = new ArrayList<>();

        for (News myNews : news ){

            String name = myNews.getTitle().toLowerCase();
            if (name.contains(newText))
                newsList.add(myNews);
        }
        mAdapter.setFilter(newsList);
        return false;
    }
}
