package com.divya.readthemall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by divya on 2/4/2018.
 */

public class SearchBookDialogFragment extends DialogFragment {

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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogCustomView = inflater.inflate(R.layout.search_book_dialog, null);
//        dialogtitle = (TextView) dialogCustomView.findViewById(R.id.dialogtitle);
//        dialogtitle.setText(title);
        builder.setTitle(title);
        builder.setView(dialogCustomView);
        builder.setPositiveButton(R.string.search, null);
        builder.setNegativeButton(R.string.cancel, null);
        return builder.create();
    }
}
