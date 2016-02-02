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

import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.adapters.MyPromoAdapter;
import com.ranapromo.nara.ranapromo3.adapters.NouveauteAdapter;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***
 * Fragment of the second tab
 */

public class PromoFragment  extends Fragment  {


    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Promotion> mak_list;
    DataBaseHelper da;
    String selectedMarque;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mak_list = new ArrayList<Promotion>();
        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        if((getArguments() != null) &&(getArguments().containsKey("marqueId"))) {
            selectedMarque = getArguments().getString("marqueId");
        }

        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerview.setLayoutManager(mLayoutManager);

        // fill the adapter with dummy data
        mAdapter = new MyPromoAdapter(getActivity(),mak_list);
       // set the adapter to the recyclerview
        mRecyclerview.setAdapter(mAdapter);
        da = new DataBaseHelper(getActivity());
        new MyAss().execute(selectedMarque);

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }



    public List<Promotion> DummyData(){

        List<Promotion> promotions = new ArrayList<>();

        int imgs[] = {R.drawable.captur,R.drawable.adidas,R.drawable.levis,R.drawable.captur,R.drawable.adidas,R.drawable.levis};
        String[] title = {"this is promo1","this is promo2","This is Promo3","This is Promo 4","This is Promo 4","This is Promo 6"};
        String[] oldPrice = {"15000","1050","6800","15000","10500","6800"};
        String[] newPrice = {"12500","9900","6000","7500","8400","5800"};
        String[] reduc = {"25","10","5","50","25","10"};
        String[] timeLeft = {"5j 20h 17m","3j 10h 51m","09j 08h 54m","15j 02h 10m","14j 20h 17m","00j 06h 15m"};
        String[] pName = {"Promo1","Promo2","Promo3","Promo4","Promo5","Promo6"};
        String[] mName = {"Adidas","Samsung","Nike","Adidas","Samsung","Nike"};
        String description = getResources().getString(R.string.textTest); // get promo's description from strings.xml

        for(int i=0;i<pName.length;i++)
        {
            Promotion current = new Promotion();
            current.setProTitre(title[i]);
            current.setProPrix(Double.parseDouble(oldPrice[i]));
            //current.newPrice = newPrice[i]; ce champs est calculable
            //
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 3);
            dt = c.getTime();

            current.setProEndDate(dt);
            current.setProTauxRed(Double.parseDouble(reduc[i]));
            //current.timeLeft = timeLeft[i]; ce champ est calculable
            current.setProDes(pName[i]);
            //current.marqueName = mName[i];//if faut trouver une solution pour avoir le nom de la marque aussi
            current.setProDes(description);
            current.setFavorite(false);
            promotions.add(current);
        }
        return  promotions;
    }




    class MyAss extends AsyncTask<String, Promotion, Void> {

        MyPromoAdapter adapter;
        String marque;
        @Override
        protected void onPreExecute() {

            adapter = (MyPromoAdapter) mRecyclerview.getAdapter();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                da.open();
                Promotion[] items;
                marque = params[0];
                if(marque == null) {
                    items = da.getAllPromo();
                } else items = da.getFavNotViewedProms(marque);
                da.close();
                Util.logDebug("adding favorite marque  to the list custom list " + items.length);
                for(Promotion item:items){
                    publishProgress(item);
                }
            } catch (Exception e) {
                Util.logError("Error loding file  "+e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Promotion... values) {
            Util.logDebug(values[0].getProTitre());
            adapter.add(values[0]);
        }
    }


}