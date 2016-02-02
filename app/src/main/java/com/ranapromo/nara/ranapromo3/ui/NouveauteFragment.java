package com.ranapromo.nara.ranapromo3.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.NouveauteAdapter;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;


import java.util.ArrayList;
import java.util.List;

/***
 * fragement of the last tab
 */
public class NouveauteFragment extends Fragment{

    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Lancement> mak_list;
    DataBaseHelper da;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mak_list = new ArrayList<Lancement>();
        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        mRecyclerview = (RecyclerView)view.findViewById(R.id.recyclerview_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mAdapter = new NouveauteAdapter(getActivity(),mak_list);
        mRecyclerview.setAdapter(mAdapter);
        da = new DataBaseHelper(getActivity());
        new MyAss().execute();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }




    class MyAss extends AsyncTask<Void, Lancement, Void> {

        NouveauteAdapter adapter;

        @Override
        protected void onPreExecute() {

            adapter = (NouveauteAdapter) mRecyclerview.getAdapter();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                da.open();
                Lancement[] items = da.getLancement();
                Util.logDebug("adding favorite marque  to the list custom list " + items.length);
                for(Lancement item:items){
                    publishProgress(item);
                }
            } catch (Exception e) {
                Util.logError("Error loding file  "+e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Lancement... values) {
            Util.logDebug(values[0].getLanDes());
            adapter.add(values[0]);
        }
    }

//    public List<Marque> FillDataPromotion(){
//
//        List<Marque> marques = new ArrayList<Marque>();
//        int imgs[] = {R.drawable.ad_banner,R.drawable.cheese_4,R.drawable.ad_banner,R.drawable.cheese_4,R.drawable.ad_banner,R.drawable.cheese_4};
//        int[] stars = {R.drawable.ic_action_favorite,R.drawable.ic_action_favorite};
//        String[] pName = {"Promo1","Promo2","Promo3","Promo4","Promo5","Promo6"};
//        String[] mName = {"ADIDAS"};
//        for(int i=0;i<mName.length;i++)
//        {
//
//            Marque current = new Marque();
//            current.setMarNom(mName[i]);
//
//            // current.starID = stars[0];
//            marques.add(current);
//            // ajouter la promo a la base de donne
//           // mDatabaseHelper.AddMarque(current);
//        }
//        return marques;
//    }
}


