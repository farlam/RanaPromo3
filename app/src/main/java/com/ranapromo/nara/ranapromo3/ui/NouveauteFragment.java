package com.ranapromo.nara.ranapromo3.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.NouveauteAdapter;

import java.util.ArrayList;
import java.util.List;

/***
 * fragement of the last tab
 */
public class NouveauteFragment extends Fragment {

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


        mAdapter = new NouveauteAdapter(getActivity(),FillDataPromotion());
        mRecyclerview.setAdapter(mAdapter);

        return view;
    }

    public List<Marque> FillDataPromotion(){

        List<Marque> marques = new ArrayList<Marque>();
        int imgs[] = {R.drawable.ad_banner,R.drawable.cheese_4,R.drawable.ad_banner,R.drawable.cheese_4,R.drawable.ad_banner,R.drawable.cheese_4};
        //int[] stars = {R.drawable.ic_action_favorite,R.drawable.ic_action_favorite};
        String[] pName = {"Promo1","Promo2","Promo3","Promo4","Promo5","Promo6"};
        String[] mName = {"Adidas","Samsung","Nike","Adidas","Samsung","Nike"};
        for(int i=0;i<pName.length;i++)
        {

            Marque current = new Marque();
            current.marqueName = mName[i];
            current.image_ID = imgs[i];
            // current.starID = stars[0];
            marques.add(current);
            // ajouter la promo a la base de donne
           // mDatabaseHelper.AddMarque(current);
        }
        return marques;
    }
}


