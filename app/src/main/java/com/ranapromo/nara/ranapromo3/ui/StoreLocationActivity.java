package com.ranapromo.nara.ranapromo3.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
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

import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.adapters.MarqueRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.adapters.StoreLocationRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * activity to find the location of every store
 */
public class StoreLocationActivity extends AppCompatActivity {
    private ArrayList<Store> mak_list;
    private StoreLocationRecyclerAdapter adapter;
    RecyclerView mRecyclerview;
    DataBaseHelper da;
    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        String val = getIntent().getStringExtra("marque");
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        mak_list = new ArrayList<Store>();
        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false); // pas de titre
        adapter = new StoreLocationRecyclerAdapter(this, mak_list);
        mRecyclerview =(RecyclerView) findViewById(R.id.recyclerview_view);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
        new MyAss().execute(val);

    }


    class MyAss extends AsyncTask<String, Store, Void> {

        StoreLocationRecyclerAdapter adapter;

        @Override
        protected void onPreExecute() {
          adapter = (StoreLocationRecyclerAdapter) mRecyclerview.getAdapter();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                da = new DataBaseHelper(StoreLocationActivity.this);
                da.open();
                Store[] items = da.getAllStoreByMarque(params[0]);
                da.close();
                Util.logDebug("adding All Store  to the list custom list " + items.length);
                for(Store item:items){
                    publishProgress(item);
                }
            } catch (Exception e) {
                Util.logError("Error loding file  "+e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Store... values) {
            Util.logDebug(values[0].getMarNom());
            adapter.add(values[0]);
        }
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
            current.setStoLieu(lieux[i]);
            //current.setS= imgs[i];
            current.setStoName(noms[i]);
            //current.setStodis[i];

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