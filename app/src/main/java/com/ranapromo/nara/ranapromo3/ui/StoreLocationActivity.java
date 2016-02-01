package com.ranapromo.nara.ranapromo3.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.adapters.StoreLocationRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * activity to find the location of every store
 */
public class StoreLocationActivity extends AppCompatActivity {

    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false); // pas de titre

        RecyclerView mRecyclerview =(RecyclerView) findViewById(R.id.recyclerview_view);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new StoreLocationRecyclerAdapter(this,DummyData()));


    }

    public static List<Store> DummyData(){
        List<Store> data = new ArrayList<>();

        int[] imgs = {R.drawable.addidas_01,R.drawable.huawei_01,R.drawable.dell_01,
                R.drawable.sam_01,R.drawable.lenovo_01};

        String[] dis = {"10km","4km","3km",
                "2km","12.5km"};

        String[] lieux = {"Alger","Blida","Oran",
                "Alger","Blida"};

        String[] noms = {"Hypermarchet Ardis","Showroom Said hamdine","FamilyShop",
                "Hypermarchet Ardis","Showroom Said hamdine"};


        for(int i=0;i<imgs.length;i++)
        {

            Store current = new Store();
            current.lieu = lieux[i];
            current.image_ID = imgs[i];
            current.name = noms[i];
            current.distance = dis[i];

            data.add(current);
        }
        return data;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_marque, menu);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_marque,menu);

        MenuItem searchItem = menu.findItem(R.id.search_button);
        MenuItemCompat.collapseActionView(searchItem);
        SearchManager searchManager= (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView=null;
        if (searchItem!=null) {
            Log.d("createOptionMenu", "search item not null");
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        }

        if (searchView!=null) {
            Log.d("createOptionMenu","search view not null");
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }else{
            Log.e("createOptionMenu","search view null");
        }

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
        if(id==android.R.id.home){
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

}