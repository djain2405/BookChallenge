package com.divya.readthemall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divya.readthemall.Model.Book;

import java.util.List;

/**
 * Created by divya on 2/12/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private List<Book> mybooklist;
    public BookAdapter(List<Book> booklist)
    {
        mybooklist = booklist;
        System.out.println("Divya " + mybooklist.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.book_list_row_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Book b = mybooklist.get(position);
        holder.title.setText(b.getBookTitle());
        holder.author.setText(b.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mybooklist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;
        public RelativeLayout foreground, background;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.booktitle);
            author = (TextView) itemView.findViewById(R.id.author);

            foreground = (RelativeLayout) itemView.findViewById(R.id.foreground);
            background = (RelativeLayout) itemView.findViewById(R.id.background);
        }
    }

    public void removeItem(int position) {
        mybooklist.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Book book, int position) {
        mybooklist.add(position, book);
        // notify item added by position
        notifyItemInserted(position);
    }
}
