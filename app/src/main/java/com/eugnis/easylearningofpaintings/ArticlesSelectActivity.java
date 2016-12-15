package com.eugnis.easylearningofpaintings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

import java.util.List;

public class ArticlesSelectActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    ListView optionsList;
    boolean stylesMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_select);

        Intent intent = getIntent();
        String mode = intent.getStringExtra(MainActivity.MODE);
        stylesMode = !(mode != null && mode.equals("painters"));

        Log.d(TAG, mode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.openDrawer(Gravity.LEFT);



        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Menu menu = navigationView.getMenu();
        //menu.clear();
        //Menu topChannelMenu = menu.addSubMenu("Стилі");

        if (!stylesMode)
        {
            //topChannelMenu.add()
            PaintersRepo paintersRepo = new PaintersRepo();
            List<Painter> paintersList= paintersRepo.getPainters();

            optionsList = (ListView) this.findViewById(R.id.optionsList);
            PaintersListAdapter adapter = new PaintersListAdapter(this, R.layout.itemlistrow, paintersList);
            optionsList.setAdapter(adapter);
            optionsList.setOnItemClickListener(this);
        }
        else {
            StylesRepo stylesRepo = new StylesRepo();
            List<Style> stylesList= stylesRepo.getStyles();

            optionsList = (ListView) this.findViewById(R.id.optionsList);
            StylesListAdapter adapter = new StylesListAdapter(this, R.layout.itemlistrow, stylesList);
            optionsList.setAdapter(adapter);
            optionsList.setOnItemClickListener(this);
        }



        //topChannelMenu.add("Foo");
        //topChannelMenu.add("Bar");
        //topChannelMenu.add("Baz");

        //navigationView.invalidate(); // Trying to force a redraw, doesn't help.

        //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.articles_select, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

class PaintersListAdapter extends ArrayAdapter<Painter> {

    public PaintersListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PaintersListAdapter(Context context, int resource, List<Painter> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Painter p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.painterList_name);
            TextView tt2 = (TextView) v.findViewById(R.id.painterList_years);
            // TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getYears());
            }
/*
            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }*/
        }

        return v;
    }

}

class StylesListAdapter extends ArrayAdapter<Style>  {

    public StylesListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public StylesListAdapter(Context context, int resource, List<Style> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Style p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.painterList_name);
            TextView tt2 = (TextView) v.findViewById(R.id.painterList_years);
            // TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                // tt2.setText(p.getAbout());
            }
/*
            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }*/
        }

        return v;
    }

}
