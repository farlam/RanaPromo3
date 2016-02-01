package com.ranapromo.nara.ranapromo3.ui;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ranapromo.nara.ranapromo3.adapters.MarquePagerAdapter;
import com.ranapromo.nara.ranapromo3.R;


public class MarqueDetail extends AppCompatActivity {

    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_marque);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false); // pas de titre


        //Set up the Tablayout
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MarquePagerAdapter(getSupportFragmentManager(), MarqueDetail.this));
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);


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
