package com.divya.readthemall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.divya.readthemall.Model.AppDatabase;
import com.divya.readthemall.Model.Book;

import java.util.List;

/**
 * Created by divya on 2/22/2018.
 */

public class ReadBooksActivity extends AppCompatActivity {

    private RecyclerView readbooklist;
    private StaggeredGridLayoutManager manager;
    private BookAdapter adapter;
    private AppDatabase db;
    private List<Book> booksData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book_activity_layout);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        readbooklist = (RecyclerView) findViewById(R.id.readbooklistview);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        readbooklist.setLayoutManager(manager);
        booksData = db.bookDao().getAllReadBooks();
        adapter = new BookAdapter(booksData, this, true);
        readbooklist.setAdapter(adapter);

    }
}
