package com.ranapromo.nara.ranapromo3.adapters;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranapromo.nara.ranapromo3.Data.MarqueDataHolder;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.comman.Util;

import com.ranapromo.nara.ranapromo3.ui.MarqueActivity;
import com.ranapromo.nara.ranapromo3.ui.MarqueDetail;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.Data.HomeData;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for the grid layout recycler in the first tab
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

private LayoutInflater inflater;

private Fragment fargment;
    private Context context;

        List<MarqueDataHolder> data = Collections.emptyList();


public HomeRecyclerAdapter(Fragment fargment, List<MarqueDataHolder> data){
        this.fargment = fargment;
        this.context = fargment.getContext();
        inflater= LayoutInflater.from(context);
        this.data = data;
    }

    public synchronized void add(MarqueDataHolder marque)    {
      data.add(0, marque);
      notifyItemInserted(0);
   }

    public void clearData() {
        int size = data.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                data.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }




@Override
public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

    View view;
        if(i != (data.size()-1))
        {
            view =  inflater.inflate(R.layout.home_marque_item, viewGroup, false);
        } else{
            view =  inflater.inflate(R.layout.marque_item, viewGroup, false);
        }

        return new ViewHolder(view);

        }

@Override
public void onBindViewHolder(HomeRecyclerAdapter.ViewHolder viewHolder, int position) {

    if(position != (data.size())){
        MarqueDataHolder current = data.get(position);
        String a = current.getOccNumber()+"";
        viewHolder.discription.setText(a);
        viewHolder.marqueID = current.getMarque();
        String dest = context.getCacheDir() + "/"+current.getMarque()+".jpg";
        if(Util.isMustDownload(dest)){
            Util.logDebug("le fichier de marque existe");
            viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(dest));
        } else
        {
            Util.logDebug("Le fichier "+dest+" n'existe pas");
            new MyImageLoader().execute(new ParamHolder(viewHolder.img, dest));
        }
        //viewHolder.img.setBackgroundResource(current.imageID);
    } else{
        viewHolder.img.setBackgroundResource(R.drawable.ic_add_black);
        viewHolder.mFrameLayout.setVisibility(View.GONE); // set visibilité gone if marque has 0 notification
    }
        }

      @Override
        public synchronized int getItemCount() {
            return data.size()+1;
        }

    class ParamHolder{
        public ParamHolder(ImageView v,String desfile){
            theView =v;
            this.desfile = desfile;
        }
        ImageView theView;
        String desfile;
    }

    class MyImageLoader extends AsyncTask<ParamHolder, Void, Bitmap> {

        ParamHolder container;
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Bitmap doInBackground(ParamHolder... params) {
            container = params[0];
            Bitmap btm = null;
            File file = new File(container.desfile);

            String fileName = file.getName();
            String url = context.getString(R.string.server_base_url)+fileName;

            Log.d(Util.DEBUG_VAL, "le fichier n'existe pas téléchargment depuis le serveur " + container.desfile);
            btm = Util.getBitmapFromURL(url, container.desfile);

            return btm;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null){
                //imageView.setBackgroundResource(mResources[position]);
                container.theView.setImageBitmap(result);
            }
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    TextView discription;
    ImageView img;
    FrameLayout mFrameLayout;
    String marqueID;


    public ViewHolder(View itemView) {
        super(itemView);
        discription = (TextView) itemView.findViewById(R.id.textnumber);
        img = (ImageView) itemView.findViewById(R.id.marqueview);
        mFrameLayout = (FrameLayout)itemView.findViewById(R.id.notif_layout);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(getAdapterPosition() == data.size())
        {
            Intent i = new Intent(context,MarqueActivity.class);
            fargment.startActivityForResult(i,100);
            //view.getContext().startActivity(i);
        } else {
            Intent intent = new Intent(context,MarqueDetail.class);
            intent.putExtra("marqueId",marqueID);
            view.getContext().startActivity(intent);
        }

    }
}
}