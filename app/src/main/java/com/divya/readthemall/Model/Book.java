package com.divya.readthemall.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by divya on 2/4/2018.
 */

@Entity
public class Book implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
        @PrimaryKey
        @NonNull
        String BookTitle;

        public String getBookTitle() {
            return BookTitle;
        }

        public void setBookTitle(String bookTitle) {
            BookTitle = bookTitle;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String author) {
            Author = author;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        String Author;
        String description;
        boolean isRead;
        String imgUrl;

        public Book(String title, String author, String description, String url)
        {
            this.BookTitle = title;
            this.Author = author;
            this.description = description;
            this.isRead = false;
            this.imgUrl = url;
        }

        public Book()
        {

        }
        public Book(Parcel in) {
            this.BookTitle = in.readString();
            this.Author = in.readString();
            this.description = in.readString();
            this.imgUrl = in.readString();

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.BookTitle);
            parcel.writeString(this.Author);
            parcel.writeString(this.description);
            parcel.writeString(this.imgUrl);
        }

}



