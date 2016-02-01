package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ranapromo.nara.ranapromo3.ui.HomeFragment;
import com.ranapromo.nara.ranapromo3.ui.NouveauteFragment;
import com.ranapromo.nara.ranapromo3.ui.PromoFragment;
import com.ranapromo.nara.ranapromo3.ui.TrendingFragment;

/**
 * Fragment PagerAdapter for tabs
 */
public class MainPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    final int PAGE_COUNT = 4;
    private Context context;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int i) {

        if (i == 0){

            return new HomeFragment();
        } else if (i == 1){

            return  new PromoFragment();
        }else if (i == 2){

            return new TrendingFragment();
        } else if (i == 3){

            return new NouveauteFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public CharSequence getPageTitle(int position) {

       // return tabTitles[position];
        return null;
    }
}
