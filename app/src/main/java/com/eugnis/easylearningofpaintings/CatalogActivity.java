package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.eugnis.easylearningofpaintings.adapters.CatalogAdapter;
import com.eugnis.easylearningofpaintings.adapters.PaintersListAdapter;
import com.eugnis.easylearningofpaintings.adapters.StylesListAdapter;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    public static final String TAG = CatalogActivity.class.getSimpleName();
    public final static String PICTURE_ID = "com.eugnis.easylearningofpaintings.PICTURE_ID";

    ListView optionsList;
    EditText searchTxt_left;
    GridView gridViewCatalog;
    int length;
    boolean stylesMode;
    DrawerLayout drawer;

    PaintersRepo paintersRepo;
    List<Painter> paintersList, painterListFull;
    PaintersListAdapter paintersListAdapter;
    StylesRepo stylesRepo;
    List<Style> stylesList, styleListFull;
    StylesListAdapter stylesListAdapter;
    PaintingsRepo paintingsRepo;
    List<Painting> paintingsList, paintingsListFull;
    CatalogAdapter paintingsCatalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity);

        Intent intent = getIntent();
        String mode = intent.getStringExtra(MainActivity.MODE);
        stylesMode = !(mode != null && mode.equals("painters"));

        Log.d(TAG, mode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.openDrawer(GravityCompat.START);

        optionsList = (ListView) this.findViewById(R.id.optionsList);
        optionsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        optionsList.setSelector(R.color.black_overlay);
        if (!stylesMode) {
            paintersRepo = new PaintersRepo();
            painterListFull = paintersRepo.getPainters();
        }
        else {
            stylesRepo = new StylesRepo();
            styleListFull= stylesRepo.getStyles();
        }
        fillList();
        fillCatalog(null);

        searchTxt_left = (EditText) findViewById(R.id.txtSearch_left);
        searchTxt_left.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                length=s.toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equals("")) {
                    fillList();
                }
                else {
                    searchItem(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < length) {
                    fillList();
                    if (!stylesMode){
                        for (Iterator<Painter> it = paintersList.iterator(); it.hasNext(); ) {
                            Painter item = it.next();
                            if(!item.getName().toLowerCase().contains(s.toString().toLowerCase())){
                                it.remove();
                            }
                        }
                        //paintersListAdapter.notifyDataSetChanged();
                    }
                    else {
                        for (Iterator<Style> it = stylesList.iterator(); it.hasNext(); ) {
                            Style item = it.next();
                            if(!item.getName().toLowerCase().contains(s.toString().toLowerCase())){
                                it.remove();
                            }
                        }
                        //stylesListAdapter.notifyDataSetChanged();
                    }

                }

            }
        });

    }

    public void searchItem (String txtToSearch){
        if (!stylesMode){
            for (Iterator<Painter> it = paintersList.iterator(); it.hasNext(); ) {
                Painter item = it.next();
                if (!item.getName().toLowerCase().contains(txtToSearch.toLowerCase())) {
                    it.remove();
                }
            }
            paintersListAdapter.notifyDataSetChanged();
        }
        else {
            for (Iterator<Style> it = stylesList.iterator(); it.hasNext(); ) {
                Style item = it.next();
                if (!item.getName().toLowerCase().contains(txtToSearch.toLowerCase())) {
                    it.remove();
                }
            }
            stylesListAdapter.notifyDataSetChanged();
        }
    }


    private void fillList(){
        if (!stylesMode)
        {
            paintersList = new ArrayList<>();
            paintersList.addAll(painterListFull);
            paintersListAdapter = new PaintersListAdapter(this, R.layout.itemlistrow, paintersList);
            optionsList.setAdapter(paintersListAdapter);
        }
        else {
            stylesList = new ArrayList<>();
            stylesList.addAll(styleListFull);
            optionsList = (ListView) this.findViewById(R.id.optionsList);
            stylesListAdapter = new StylesListAdapter(this, R.layout.itemlistrow, stylesList);
            optionsList.setAdapter(stylesListAdapter);
        }
        optionsList.setOnItemClickListener(this);
    }

    private void fillCatalog(Integer filterID){
        paintingsRepo = new PaintingsRepo();
        if (filterID==null) paintingsList= paintingsRepo.getPaintings();    //ПОМЕНЯТЬ!!!
        else
        {
            if (!stylesMode) paintingsList= paintingsRepo.getFilteredPaintings(Painter.TAG, filterID);
            else paintingsList= paintingsRepo.getFilteredPaintings(Style.TAG, filterID);
        }


        gridViewCatalog = (GridView) findViewById(R.id.gridViewCatalog);
        paintingsCatalogAdapter = new CatalogAdapter(this, R.layout.itemlistrow, paintingsList);
        gridViewCatalog.setAdapter(paintingsCatalogAdapter);

        gridViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Painting p = (Painting)parent.getItemAtPosition(position);
                Log.d(TAG, p.getName());

                Intent intent = new Intent(CatalogActivity.this, PaintingView.class);
                intent.putExtra(PICTURE_ID, Integer.toString(p.getPaintingID()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Integer filterID=null;
        if (!stylesMode)
        {
            Painter p = (Painter) parent.getItemAtPosition(position);
            Log.d(TAG, p.getName());
            filterID = p.getPainterID();
        }
        else
        {
            Style p = (Style) parent.getItemAtPosition(position);
            Log.d(TAG, p.getName());
            filterID = p.getStyleID();
        }
        optionsList.setItemChecked(position, true);
        //view.setBackgroundColor(Color.BLUE);

        fillCatalog(filterID);
        drawer.closeDrawer(GravityCompat.START);
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




    public void backToMenu(View v) {

        finish();
    }
}

