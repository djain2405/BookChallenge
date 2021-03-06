package com.divya.readthemall;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.divya.readthemall.Model.Book;

import java.io.InputStream;
import java.util.BitSet;

/**
 * Created by divya on 2/14/2018.
 */

public class BookDetailsActivity extends AppCompatActivity {

   // private ImageView bookImage;
    private CollapsingToolbarLayout toolbarLayout;
    private TextView author;
    private TextView description;
    private Book b;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details_layout);
        setTitle(getResources().getString(R.string.details_title));
        //bookImage =(ImageView) findViewById(R.id.bookimage);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        b = getIntent().getExtras().getParcelable("BOOK_VAL");
        new DownloadImageTask((ImageView) findViewById(R.id.bookimage))
                .execute(b.getImgUrl());
        toolbarLayout.setTitle(b.getBookTitle());
        author = (TextView) findViewById(R.id.authortext);
        description = (TextView) findViewById(R.id.description);

        author.setText(b.getAuthor());
        description.setText(Html.fromHtml(b.getDescription()));


    }


}
