package com.divya.readthemall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by divya on 2/4/2018.
 */

public class SearchBookDialogFragment extends DialogFragment {

    private NetworkFragment networkFragment;
    private boolean isDownloading = false;
    public static SearchBookDialogFragment newInstance(int title)
    {
        SearchBookDialogFragment frag = new SearchBookDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }


    private TextView dialogtitle;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int title = getArguments().getInt("title");
        networkFragment = NetworkFragment.getInstance(getFragmentManager(), "https://www.goodreads.com//book/title.xml?key=k74Y8p6u0WAR2JocvERqZw&title=Odyssey");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogCustomView = inflater.inflate(R.layout.search_book_dialog, null);
        builder.setTitle(title);
        builder.setView(dialogCustomView);
        builder.setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
  //              startDownload();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                finishDownloading();
            }
        });
        return builder.create();
    }

    private void startDownload() {

        if(!isDownloading && networkFragment != null)
        {
            networkFragment.startDownload();
            isDownloading = true;
        }
    }

}
