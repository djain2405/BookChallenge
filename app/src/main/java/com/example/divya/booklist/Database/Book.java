package com.example.divya.booklist.Database;

import android.arch.persistence.room.Entity;

/**
 * Created by divya on 1/2/2018.
 */


@Entity(tableName = "book")
public class Book {

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    String bookName;
    boolean isRead;

}
