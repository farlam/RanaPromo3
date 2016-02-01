package com.ranapromo.nara.ranapromo3.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.MyFavorieAdapter;
import com.ranapromo.nara.ranapromo3.adapters.MyPromoAdapter;

import java.util.ArrayList;
import java.util.List;

/***
 *  First Activity from the nav drawer
 */
public class FavorieActivity extends AppCompatActivity {

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
        mRecyclerview.setBackgroundColor(ContextCompat.getColor(this,R.color.grey50));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mAdapter = new MyFavorieAdapter(this,DummyData());
        mRecyclerview.setAdapter(mAdapter);

    }

    public List<Promotion> DummyData(){

        List<Promotion> promotions = new ArrayList<>();

        int imgs[] = {R.drawable.captur,R.drawable.adidas,R.drawable.levis,
                R.drawable.captur,R.drawable.adidas,R.drawable.levis};
        String[] oldPrice = {"15.000 DA","10.500 DA","6.800 DA","15.000 DA","10.500 DA","6.800 DA"};
        String[] newPrice = {"12.500 DA","9.900 DA","6.000 DA","7.500 DA","8.400 DA","5.800 DA"};
        String[] reduc = {"25%","10%","5%","50%","25%","10%"};
        String[] timeLeft = {"5j 20h 17m","3j 10h 51m","09j 08h 54m","15j 02h 10m","14j 20h 17m","00j 06h 15m"};
        String[] pName = {"Promo1","Promo2","Promo3","Promo4","Promo5","Promo6"};
        String[] mName = {"Adidas","Samsung","Nike","Adidas","Samsung","Nike"};
        String description = getResources().getString(R.string.textTest); // get promo's description from strings.xml

        for(int i=0;i<pName.length;i++)
        {
            Promotion current = new Promotion();
            current.oldPrice = oldPrice[i];
            current.newPrice = newPrice[i];
            current.reduction = reduc[i];
            current.timeLeft = timeLeft[i];
            current.promotionName = pName[i];
            current.marqueName = mName[i];
            current.image_ID = imgs[i];
            current.description = description;
            current.favorite = 0;

            promotions.add(current);
        }
        return  promotions;
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
