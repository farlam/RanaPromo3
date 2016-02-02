package com.ranapromo.nara.ranapromo3.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.adapters.PromoDetailViewPagerAdapter;
import com.ranapromo.nara.ranapromo3.R;

import me.relex.circleindicator.CircleIndicator;

/***
 * promotion detail activity with viewpager
 */
public class PromoDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Promotion promo = getIntent().getParcelableExtra("promo"); // get intent object from MyPromoAdapter

        setContentView(R.layout.activity_produit);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        int[] promoImg = new int[]{promo.image_ID,promo.image_ID,promo.image_ID}; // use it to fill the pageAdapter with promo imgs

        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        defaultViewpager.setAdapter(new PromoDetailViewPagerAdapter(this,promoImg));
        defaultIndicator.setViewPager(defaultViewpager);


        TextView textView = (TextView) findViewById(R.id.detail_text);
        textView.setText(promo.getProDes());

        TextView promoNameView = (TextView) findViewById(R.id.promo_name);
        promoNameView.setText(promo.getProTitre());

        TextView newPriceView = (TextView) findViewById(R.id.new_promo_prix);
        newPriceView.setText(promo.getNewPrice().toString());

        TextView timeLeftView = (TextView) findViewById(R.id.count_down_text);
        timeLeftView.setText(promo.getTimeLeft().toString());

        TextView reductionView = (TextView) findViewById(R.id.reduction);
        reductionView.setText(promo.getProTauxRed().toString());

        TextView acheter = (TextView) findViewById(R.id.ou_acheter);

        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),StoreLocationActivity.class);
                i.putExtra("marque",promo.getMarNom());
                startActivity(i);
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marque, menu);
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
