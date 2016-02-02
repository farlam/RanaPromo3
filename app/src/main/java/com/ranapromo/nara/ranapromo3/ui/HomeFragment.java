package com.ranapromo.nara.ranapromo3.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapromo.nara.ranapromo3.Data.MarqueDataHolder;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.HomeRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.adapters.HomeViewPagerAdapter;
import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.adapters.MyPromoAdapter;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/***
 * fragment of the first tab
 ***/

public class HomeFragment extends Fragment  {
    private ArrayList<MarqueDataHolder> mak_list;

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    private  Context contex;


    /*@Override
      public void onResume() {
        Util.logDebug("Fragment Home resumed");
        new MyAss().execute();
        super.onResume();
    }*/

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
    public void onAttach(Context context) {
        Util.logDebug("Fragment attached to activies");
        this.contex = context;
        super.onAttach(context);
    }

    /*@Nullable
    @

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mak_list = new ArrayList<MarqueDataHolder>();
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        mRecyclerview = (RecyclerView) view.findViewById(R.id.home_recycler_view);

        // test
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        GridLayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(),3);
        mRecyclerview.setLayoutManager(mLayoutManager);


        mAdapter = new HomeRecyclerAdapter(getActivity(),mak_list);
        mRecyclerview.setAdapter(mAdapter);


        ViewPager defaultViewpager = (ViewPager) view.findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) view.findViewById(R.id.indicator_default);
        defaultViewpager.setAdapter(new HomeViewPagerAdapter(getActivity()));
        defaultIndicator.setViewPager(defaultViewpager);

        return view;
    }*/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mak_list = new ArrayList<MarqueDataHolder>();
        mRecyclerview = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        // test
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        GridLayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(),3);
        mRecyclerview.setLayoutManager(mLayoutManager);
        //mAdapter = new HomeRecyclerAdapter(getActivity(),mak_list);
        mAdapter = new HomeRecyclerAdapter(this,mak_list);

        mRecyclerview.setAdapter(mAdapter);

        ViewPager defaultViewpager = (ViewPager) view.findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) view.findViewById(R.id.indicator_default);
        defaultViewpager.setAdapter(new HomeViewPagerAdapter(getActivity()));
        defaultIndicator.setViewPager(defaultViewpager);
        new MyAss().execute();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100){
            new MyAss().execute();
        }
        Util.logDebug("Activity terminated and return value "+requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    class MyAss extends AsyncTask<Void, MarqueDataHolder, Void> {

        HomeRecyclerAdapter adapter;

        @Override
        protected void onPreExecute() {
            adapter = (HomeRecyclerAdapter) mRecyclerview.getAdapter();
            adapter.clearData();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                DataBaseHelper da;
                da = new DataBaseHelper(getActivity());
                da.open();
                MarqueDataHolder[] items = da.getBestMarques3();
                da.close();
                Util.logDebug("adding favorite marque  to the list custom list " + items.length);
                for(MarqueDataHolder item:items){
                    publishProgress(item);
                }
            } catch (Exception e) {
                Util.logError("Error loding file  "+e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(MarqueDataHolder... values) {
            Util.logDebug(values[0].getMarque());
            adapter.add(values[0]);
        }
    }

    public static List<HomeData> getData(){
        List<HomeData> data = new ArrayList<>();
        int[] imgs = {R.drawable.addidas_01,R.drawable.huawei_01,R.drawable.dell_01,
                R.drawable.sam_01,R.drawable.lenovo_01,R.drawable.addidas_01,
                R.drawable.huawei_01,R.drawable.sam_01,R.drawable.dell_01,
                R.drawable.lenovo_01};
        String[] disc = {"1","4","3",
                "2","1","1",
                "3","2","4",
                "1"};

        for(int i=0;i<disc.length;i++)
        {

            HomeData current = new HomeData();
            current.discription = disc[i];
            current.imageID = imgs[i];

            data.add(current);
        }
        return data;
    }
}