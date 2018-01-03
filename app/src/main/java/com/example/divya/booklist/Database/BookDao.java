package com.example.divya.booklist.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by divya on 1/2/2018.
 */

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert
    void insert(Book book);

    @Insert
    void insertAll(Book... books);


}
