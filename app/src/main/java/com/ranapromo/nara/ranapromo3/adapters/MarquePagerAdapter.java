package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ranapromo.nara.ranapromo3.ui.PromoFragment;


/**
 * Fragment pager adapter for the marque activity tab
 */
public class MarquePagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Promos", "Nouveautes"};
    private Context context;
    PromoFragment promoFragment;
    String marqueId = null;
    public MarquePagerAdapter(FragmentManager fm, Context context,String marqueId) {
        super(fm);
        this.context = context;
        this.marqueId = marqueId;
    }


    @Override
    public Fragment getItem(int i) {

        if (i == 0){
            promoFragment = new PromoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("marqueId", marqueId);
            promoFragment.setArguments(bundle);
        return promoFragment;
        } else if (i == 1){

        return  new PromoFragment();
        }
        return null;
        }

    @Override
    public int getCount() {
        return PAGE_COUNT;
        }

    public CharSequence getPageTitle(int position) {

         return tabTitles[position];

        }
}