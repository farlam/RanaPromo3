package com.ranapromo.nara.ranapromo3.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.MyTrendingAdapter;

import java.util.ArrayList;
import java.util.List;

/***
 * fragment for the third tab
 */

public class TrendingFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);


        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());

        mRecyclerview.setLayoutManager(mLayoutManager);

        // feed the adapter with dummy data
        mAdapter = new MyTrendingAdapter(getActivity(),DummyData());
        mRecyclerview.setAdapter(mAdapter);

        return view;

    }



    public List<Promotion> DummyData(){

        List<Promotion> promotions = new ArrayList<>();

        int imgs[] = {R.drawable.captur,R.drawable.adidas,R.drawable.levis,R.drawable.captur,R.drawable.adidas,R.drawable.levis};
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
}


