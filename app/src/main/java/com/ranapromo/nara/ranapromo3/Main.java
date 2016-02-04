package com.ranapromo.nara.ranapromo3;
import android.app.SearchManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ranapromo.nara.ranapromo3.adapters.MainPagerAdapter;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.ui.FavorieActivity;
import com.ranapromo.nara.ranapromo3.ui.StoreActivity;


public class Main extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private Toolbar toolbar;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationView mNavigationView;


    private CharSequence mTitle;
    private int position;
    private ViewPager mPager;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // FillDataMarque();
      //  FillDataPromotion();

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main1);



        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Drawable drawabl = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu, null);
        drawabl = DrawableCompat.wrap(drawabl);
        //DrawableCompat.setTint(drawabl, ContextCompat.getColor(this, R.color.primaryColor));

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawabl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set up the Tablayout
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), Main.this));
        mPager.addOnPageChangeListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mPager);

        TextView tabOne = (TextView)getLayoutInflater().inflate(R.layout.costum_tab,null);
        tabOne.setText(" MON ESPACE");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0, 0);
        mTabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView)getLayoutInflater().inflate(R.layout.costum_tab,null);
        tabTwo.setText(" LES PROMOS");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_promos, 0, 0);
        mTabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabTree = (TextView)getLayoutInflater().inflate(R.layout.costum_tab,null);
        tabTree.setText(" POPULAIRES");
        tabTree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_populaires, 0, 0);
        mTabLayout.getTabAt(2).setCustomView(tabTree);

        TextView tabFour = (TextView)getLayoutInflater().inflate(R.layout.costum_tab,null);
        tabFour.setText(" NOUVEAUTES");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_nouveautes, 0, 0);
        mTabLayout.getTabAt(3).setCustomView(tabFour);


        // Set up the drawer.
        mNavigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        if (mPager != null) {
                            switch (menuItem.getItemId()) {
                                case R.id.drawer_mes_favorie:
                                    Intent intent = new Intent(getBaseContext(),FavorieActivity.class);
                                    startActivity(intent);
                                    break;
                                case R.id.drawer_promo_de_semaine:

                                    break;
                                case R.id.drawer_marque:
                                    Intent i = new Intent(getBaseContext(),StoreActivity.class);
                                    startActivity(i);
                                    break;
                                case R.id.drawer_notification:

                                    break;
                                case R.id.drawer_aides:

                                    break;
                                case R.id.drawer_apropos:

                                    break;

                            }
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {

            MenuInflater mi = getMenuInflater();
            mi.inflate(R.menu.menu_main, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setMaxWidth(400);
            searchView.setQueryHint("hawes wech thab");
            searchView.clearFocus();

        }*/

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

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
        return super.onCreateOptionsMenu(menu);
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
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


        switch (position){
            case 0:
                SpannableString s = new SpannableString("MON ESPACE");
                s.setSpan(new RelativeSizeSpan(0.75f), 0, s.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                getSupportActionBar().setTitle(s);
                break;
            case 1:
                SpannableString p = new SpannableString("LES PROMOTIONS");
                p.setSpan(new RelativeSizeSpan(0.75f), 0, p.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                getSupportActionBar().setTitle(p);
                break;
            case 2:
                SpannableString a = new SpannableString("LES POPULAIRES");
                a.setSpan(new RelativeSizeSpan(0.75f), 0, a.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                getSupportActionBar().setTitle(a);
                break;
            case 3:
                SpannableString n = new SpannableString("LES NOUVEAUTES");
                n.setSpan(new RelativeSizeSpan(0.75f), 0, n.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                getSupportActionBar().setTitle(n);
                break;
            default:
                getSupportActionBar().setTitle("Rana promo");
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
