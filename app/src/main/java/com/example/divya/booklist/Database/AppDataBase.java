package com.example.divya.booklist.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by divya on 1/2/2018.
 */

@Database(entities = {Book.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    public abstract BookDao bookDao();

    public static AppDataBase getAppDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "book-database").allowMainThreadQueries().build();
        }

        return INSTANCE;

    }

    public static void destroyInstance()
    {
        INSTANCE = null;
    }

}
