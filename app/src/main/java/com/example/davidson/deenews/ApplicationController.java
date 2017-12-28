package com.example.davidson.deenews;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.davidson.deenews.db.DeeNewsDb;

/**
 * Created by idorenyin on 12/28/17.
 */

public class ApplicationController extends Application {

    private static DeeNewsDb newsDb;

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the db once during the app lifecycle
        newsDb =  Room.databaseBuilder(
                getApplicationContext(),
                DeeNewsDb.class,
                "news.db")
                .build();
    }


    public static DeeNewsDb provideDb(){
        return newsDb;
    }

}
