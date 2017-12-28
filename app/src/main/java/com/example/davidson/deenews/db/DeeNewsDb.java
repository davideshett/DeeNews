package com.example.davidson.deenews.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.davidson.deenews.model.News;

/**
 * Created by idorenyin on 12/28/17.
 */

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class DeeNewsDb extends RoomDatabase {

    public abstract NewsDao newsDao();

}
