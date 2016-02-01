package com.ranapromo.nara.ranapromo3.ui;

import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.HomeRecyclerAdapter;
import com.ranapromo.nara.ranapromo3.adapters.HomeViewPagerAdapter;
import com.ranapromo.nara.ranapromo3.Data.HomeData;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/***
 * fragment of the first tab
 ***/

public class HomeFragment extends Fragment {

    List<HomeData> emptyData = new ArrayList<>(); // empty list for test

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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.home_fragment, container, false);

         RecyclerView mRecyclerview = (RecyclerView) view.findViewById(R.id.home_recycler_view);

        // test
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        GridLayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(),3);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getActivity());


        if(emptyData.isEmpty()){
            // use Linear Layout manager if there is no data ( aucune marque favorite )
            mRecyclerview.setLayoutManager(mLinearLayoutManager);
        } else{
            // use Grid Layout manager if there is data ( il ya des marque favorite)
            mRecyclerview.setLayoutManager(mLayoutManager);
        }



       // RecyclerView.Adapter mAdapter = new HomeRecyclerAdapter(getActivity(),getData());  // Dummy Data
        RecyclerView.Adapter mAdapter = new HomeRecyclerAdapter(getActivity(), emptyData);  // empty list
        mRecyclerview.setAdapter(mAdapter);


        ViewPager defaultViewpager = (ViewPager) view.findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) view.findViewById(R.id.indicator_default);
        defaultViewpager.setAdapter(new HomeViewPagerAdapter(getActivity()));
        defaultIndicator.setViewPager(defaultViewpager);

        return view;
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