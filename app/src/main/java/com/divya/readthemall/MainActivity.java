package com.divya.readthemall;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.divya.readthemall.Model.AppDatabase;
import com.divya.readthemall.Model.BookAdapter;

public class MainActivity extends AppCompatActivity {

    private boolean isDownloading = false;
    private NetworkFragment networkFragment;
    private TextView text;
    private AppDatabase db;
    private RecyclerView booklist;
    private BookAdapter adapter;
    private DividerItemDecoration decoration;
    private LinearLayoutManager manager;

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
        adapter = new BookAdapter(db.bookDao().getAll());
        booklist.setAdapter(adapter);
        decoration = new DividerItemDecoration(booklist.getContext(), manager.getOrientation());
        booklist.addItemDecoration(decoration);
        //text.setText(String.valueOf(db.bookDao().getAll().size()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                SearchBookDialogFragment myfragment = SearchBookDialogFragment.newInstance(R.string.search_dialog_title);
//                myfragment.show(getFragmentManager(), "Books");

                //Intent i = new Intent(getApplicationContext(), AddBookActivity.class);
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

        return super.onOptionsItemSelected(item);
    }


}
