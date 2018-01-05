package com.example.divya.booklist.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

    @PrimaryKey @NonNull
    String bookName;

    @ColumnInfo
    boolean isRead;

}
