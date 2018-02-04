package com.divya.readthemall.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by divya on 2/4/2018.
 */

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;
    public abstract BookDao bookDao();

    public static AppDatabase getAppDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "book-database").allowMainThreadQueries().build();

        }

        return INSTANCE;

    }

    public static void destroyInstance()
    {
        INSTANCE = null;
    }
}
