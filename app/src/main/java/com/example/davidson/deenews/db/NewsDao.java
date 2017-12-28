package com.example.davidson.deenews.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidson.deenews.model.News;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by idorenyin on 12/28/17.
 */

@Dao
public interface NewsDao {

    @Insert(onConflict = REPLACE)
    void insert(News result);

    @Delete
    void delete(News result);

    @Query("SELECT * FROM " + News.TABLE_NAME+ " WHERE source IS :source")
    List<News> getNews(String source);

    /*@Query("SELECT * FROM " + News.TABLE_NAME+ " WHERE source IS :source")
    List<News> getAllCNNNews(String source);

    @Query("SELECT * FROM " + News.TABLE_NAME+ " WHERE source IS :source")
    List<News> getAllBBCNews(String source);*/



}
