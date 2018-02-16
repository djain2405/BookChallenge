package com.divya.readthemall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.divya.readthemall.Model.AppDatabase;
import com.divya.readthemall.Model.Book;

import java.util.List;

/**
 * Created by divya on 2/6/2018.
 */

public class AddBookActivity extends FragmentActivity implements DownloadCallback {

    private EditText addName;
    private Button search;
    private RelativeLayout layout;
    private TextView booktitle;
    private TextView authorname;
    private TextView didyoumean;
    private boolean isDownloading = false;
    private NetworkFragment networkFragment;
    private AppDatabase db;
    private List<Book> bookList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_layout);
        setTitle(getResources().getString(R.string.add_title));
        addName = (EditText) findViewById(R.id.addBookName);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        search = (Button) findViewById(R.id.search);

        db = AppDatabase.getAppDatabase(getApplicationContext());

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Divya onClick called");
                String bookname = addName.getText().toString();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addName.getWindowToken(), 0);
                startDownload(bookname);
            }
        });

    }


    private void startDownload(String bookname) {
        String []str = bookname.split(" ");
        bookname="";
        for(String s : str)
        {
            bookname += s+"+";
        }
        networkFragment = NetworkFragment.getInstance(getFragmentManager(), "https://www.goodreads.com//book/title.xml?key=k74Y8p6u0WAR2JocvERqZw&title="+bookname.substring(0, bookname.length()-1));
        if(!isDownloading && networkFragment != null)
        {
            networkFragment.startDownload();
            isDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(Book result) {
        if (result != null) {
            System.out.println("Divya Result " + result);
            displayBookName(result);
        } else {
            System.out.println("Divya Exception");
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {

    }

    @Override
    public void finishDownloading() {
        isDownloading = false;
        if(networkFragment != null)
        {
            networkFragment.cancelDownload();
        }
    }

    void displayBookName(final Book book)
    {
        layout = (RelativeLayout) findViewById(R.id.bgsearch);
        booktitle = (TextView) findViewById(R.id.booktitle);
        authorname = (TextView) findViewById(R.id.author);
        didyoumean = (TextView) findViewById(R.id.didyoumeantext);

        booktitle.setText(book.getBookTitle());
        authorname.setText("By "+book.getAuthor());
        layout = (RelativeLayout) findViewById(R.id.bgsearch);
        layout.setVisibility(View.VISIBLE);
        booktitle.setVisibility(View.VISIBLE);
        authorname.setVisibility(View.VISIBLE);
        didyoumean.setVisibility(View.VISIBLE);

        booktitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savetoDb(book);
            }
        });
    }

     void savetoDb(Book book)
     {
            bookList = db.bookDao().getAll();
            bookList.add(book);
            if(db.bookDao().findBookByTitle(book.getBookTitle()) == null) {
                db.bookDao().insert(book);
                Intent i = new Intent();
                i.putExtra("BOOK_ADDED", book);
                Toast.makeText(this, "The book " + book.getBookTitle() + " is saved to your reading list", Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK, i);
            }
            else
            {
                Toast.makeText(this, "The book " + book.getBookTitle() + " is already in your reading list", Toast.LENGTH_LONG).show();
            }

            finish();
     }
}
