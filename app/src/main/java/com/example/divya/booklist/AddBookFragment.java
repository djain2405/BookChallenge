package com.example.divya.booklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by divya on 1/1/2018.
 */

public class AddBookFragment extends DialogFragment {

    OnBookAddedListener callBack;

    public interface OnBookAddedListener{
        public void onBookAdded(String bookName);
    }

    private EditText bookDetails;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View myCustomView = inflater.inflate(R.layout.add_book_dialog, null);
        bookDetails = (EditText) myCustomView.findViewById(R.id.booknameedit);
        builder.setView(myCustomView);
        builder.setPositiveButton("Add Book", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               callBack.onBookAdded(bookDetails.getText().toString());
               AddBookFragment.this.getDialog().dismiss();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddBookFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callBack = (OnBookAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
