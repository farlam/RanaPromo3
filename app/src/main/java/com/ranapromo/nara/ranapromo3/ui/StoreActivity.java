package com.ranapromo.nara.ranapromo3.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.StoreRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Store activity from the nav drawer
 */
public class StoreActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;


    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favorie_fragment);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview_favorie);
        mRecyclerview.setBackgroundResource(R.drawable.splash_screen);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mAdapter = new StoreRecyclerAdapter(this,DummyData());
        mRecyclerview.setAdapter(mAdapter);

    }

    public static List<HomeData> DummyData(){
        List<HomeData> data = new ArrayList<>();
        int[] imgs = {R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur};
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