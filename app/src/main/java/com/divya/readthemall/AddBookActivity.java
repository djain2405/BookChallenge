package com.divya.readthemall;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by divya on 2/6/2018.
 */

public class AddBookActivity extends FragmentActivity implements DownloadCallback {

    private EditText addName;
    private Button search;
    private boolean isDownloading = false;
    private NetworkFragment networkFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_layout);

        addName = (EditText) findViewById(R.id.addBookName);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookname = addName.getText().toString();
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
    public void updateFromDownload(String result) {
        if (result != null) {
            System.out.println("Divya Result" + result);
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
}
