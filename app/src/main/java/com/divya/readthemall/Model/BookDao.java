package com.divya.readthemall.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.ABORT;

/**
 * Created by divya on 2/4/2018.
 */

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert(onConflict = ABORT)
    void insert(Book book);

    @Insert
    void insertAll(Book... books);

    @Query("SELECT * FROM book WHERE book.BookTitle LIKE :name")
    Book findBookByTitle(String name);

    @Query("SELECT * FROM book WHERE book.isRead = 0")
    List<Book> getAllUnreadBooks();

    @Query("SELECT * FROM book WHERE book.isRead = 1")
    List<Book> getAllReadBooks();

    @Query("DELETE FROM book")
    void deleteAll();

    @Update
    void update(Book book);
}
