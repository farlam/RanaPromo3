package com.ranapromo.nara.ranapromo3.ui;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.adapters.MarqueRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.R;


import java.util.ArrayList;
import java.util.List;

/***
 *   Marque activity with costum grid layout recyclerview
 */
public class MarqueActivity extends AppCompatActivity {


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildLayoutPosition(view) == 0 ||parent.getChildLayoutPosition(view) == 1||parent.getChildLayoutPosition(view) == 2)
                outRect.top = space;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marque);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Marques");


        RecyclerView mRecyclerview = (RecyclerView) findViewById(R.id.marque_recycler_view);

        // set item decoration to the recyclerview
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        GridLayoutManager mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerview.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new MarqueRecyclerAdapter(this,DummyData());
        mRecyclerview.setAdapter(mAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }


    public static List<HomeData> DummyData(){
        List<HomeData> data = new ArrayList<>();
        int[] imgs = {R.drawable.addidas_01,R.drawable.huawei_01,R.drawable.dell_01,
                R.drawable.sam_01,R.drawable.lenovo_01,R.drawable.addidas_01,
                R.drawable.huawei_01,R.drawable.sam_01,R.drawable.dell_01,
                R.drawable.lenovo_01,R.drawable.huawei_01,R.drawable.addidas_01,
                R.drawable.huawei_01,R.drawable.dell_01,R.drawable.sam_01,
                R.drawable.lenovo_01,R.drawable.addidas_01,R.drawable.huawei_01,
                R.drawable.sam_01,R.drawable.dell_01,R.drawable.lenovo_01,
                R.drawable.huawei_01};
        String[] disc = {"1","4","3",
                "2","1","1",
                "3","2","4",
                "2","1","1",
                "4","3","2",
                "1","1","3",
                "2","4","2",
                "1"};

        Boolean[] fav = {true,false,false,
                false,false,false,
                false,false,false,
                false,true,false,
                false,false,false,
                false,false,true,
                true,false,false,
                false};

        for(int i=0;i<disc.length;i++)
        {

            HomeData current = new HomeData();
            current.discription = disc[i];
            current.imageID = imgs[i];
            current.fav = fav[i];

            data.add(current);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_marque, menu);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_marque, menu);

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
