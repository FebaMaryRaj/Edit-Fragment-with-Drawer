package com.feba.listfragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.feba.listfragment.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener,
        EditFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ItemFragment itemFragment = new ItemFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame1, itemFragment, "MainFragment").commit();
        ;
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

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
        EditFragment editFragment = new EditFragment(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame2, editFragment, "EditFragment")
                .addToBackStack("EditFragment").commit();

    }

    @Override
    public void onFragmentInteraction(boolean refreshRequested) {
        Toast.makeText(this, "Done with edit fragment", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager()
                .findFragmentByTag("EditFragment")).commit();


        if (refreshRequested) {
            ItemFragment itemFragment = (ItemFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
            itemFragment.notifyDataChanged();
        }

    }
}
