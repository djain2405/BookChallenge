package com.divya.readthemall;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divya.readthemall.Model.Book;

import java.util.List;

/**
 * Created by divya on 2/12/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private List<Book> mybooklist;
    private Context context;
    private boolean isRead;
    public BookAdapter(List<Book> booklist, Context context, boolean isRead)
    {
        mybooklist = booklist;
        this.context = context;
        this.isRead = isRead;
        System.out.println("Divya " + mybooklist.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = null;
        if(isRead == false) {
            v = inflater.inflate(R.layout.book_list_row_layout, parent, false);
        }
        else
        {
            v = inflater.inflate(R.layout.read_book_grid_layout, parent, false);
        }
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Book b = mybooklist.get(position);
        if(isRead == true)
        {
            holder.img.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        holder.title.setText(b.getBookTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BookDetailsActivity.class);
                i.putExtra("BOOK_VAL", b);
                context.startActivity(i);
            }
        });
        holder.author.setText(b.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mybooklist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;
        private ImageView img;
        public RelativeLayout foreground, background;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.booktitle);
            author = (TextView) itemView.findViewById(R.id.author);
            img = (ImageView) itemView.findViewById(R.id.img);

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
