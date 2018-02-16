package com.divya.readthemall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.divya.readthemall.Model.AppDatabase;
import com.divya.readthemall.Model.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private boolean isDownloading = false;
    private NetworkFragment networkFragment;
    private TextView text;
    private AppDatabase db;
    private RecyclerView booklist;
    private BookAdapter adapter;
    private DividerItemDecoration decoration;
    private LinearLayoutManager manager;
    private List<Book> booksData;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //text = (TextView) findViewById(R.id.testText);
        db = AppDatabase.getAppDatabase(getApplicationContext());
        booklist = (RecyclerView) findViewById(R.id.booklist);
        manager = new LinearLayoutManager(this);
        booklist.setLayoutManager(manager);
        booksData = db.bookDao().getAllUnreadBooks();

        adapter = new BookAdapter(booksData, this);
        booklist.setAdapter(adapter);
        decoration = new DividerItemDecoration(booklist.getContext(), manager.getOrientation());
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_line));
        booklist.addItemDecoration(decoration);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT , this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(booklist);


        //text.setText(String.valueOf(db.bookDao().getAll().size()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                SearchBookDialogFragment myfragment = SearchBookDialogFragment.newInstance(R.string.search_dialog_title);
//                myfragment.show(getFragmentManager(), "Books");


                startActivityForResult(new Intent(getApplicationContext(), AddBookActivity.class), 1);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                booksData.add((Book)data.getExtras().getParcelable("BOOK_ADDED"));
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            StringBuilder sb = new StringBuilder();
            sb.append(getResources().getString(R.string.sharing_title));
            for(Book b : booksData)
            {
                sb.append("\n"+b.getBookTitle()+" By "+b.getAuthor());
            }
            shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());

            startActivity(Intent.createChooser(shareIntent, "Choose how to share!"));
            return true;
            //shareIntent.putParcelableArrayListExtra("My BookList", booksData);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof BookAdapter.MyViewHolder && direction == ItemTouchHelper.RIGHT)
        {
            String name = booksData.get(viewHolder.getAdapterPosition()).getBookTitle();
            final Book deletedItem = booksData.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            adapter.removeItem(deletedIndex);
            Book deletedBook = db.bookDao().findBookByTitle(deletedItem.getBookTitle());
            deletedBook.setRead(true);
            db.bookDao().update(deletedBook);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.snackbar_text, name), Snackbar.LENGTH_LONG );
            snackbar.setAction(getResources().getString(R.string.undo), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    Book temp = db.bookDao().findBookByTitle(deletedItem.getBookTitle());
                    temp.setRead(false);
                    db.bookDao().update(temp);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
//            booksData.remove(viewHolder.getAdapterPosition());
//            adapter.notifyDataSetChanged();
//            System.out.println("Divya size now " + db.bookDao().getAll().size());
            // remove the item from recycler view
            //adapter.removeItem(viewHolder.getAdapterPosition());

        }

    }
}
