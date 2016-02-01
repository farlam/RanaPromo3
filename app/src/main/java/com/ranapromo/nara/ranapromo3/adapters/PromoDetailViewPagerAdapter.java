package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ranapromo.nara.ranapromo3.R;

/**
 * Pager adapter for images in the promo detail activity
 */
public class PromoDetailViewPagerAdapter extends PagerAdapter {

    int[] mResources = new int[]{};

    Context mContext;
    LayoutInflater mLayoutInflater;


    public PromoDetailViewPagerAdapter(Context context, int[] imgs) {
        mContext = context;
        mResources = imgs;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (LinearLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        // imageView.setImageResource(mResources[position]);
        imageView.setBackgroundResource(mResources[position]);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

