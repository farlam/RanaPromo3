package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.ui.PromoDetailActivity;
import com.ranapromo.nara.ranapromo3.R;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for the nouveauté fragment in the last tab
 */
public class NouveauteAdapter extends RecyclerView.Adapter<NouveauteAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Lancement> data = Collections.emptyList();

    public NouveauteAdapter(Context context, List<Lancement> data){
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }


    public void add(Lancement model){
        data.add(0,model);
        notifyItemInserted(0);
    }

    @Override
    public NouveauteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view =  inflater.inflate(R.layout.nouveaute_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NouveauteAdapter.ViewHolder viewHolder, int position) {
        Lancement current = data.get(position);
        //  viewHolder.discription.setText(current.discription)
        viewHolder.fav = current.isFavorite();
        viewHolder.txtView.setText(current.getLanTitre());
        String dest = context.getCacheDir() + "/" + "LANCEMENT_" +current.getLanPid()+"_1.jpg";
        if(Util.isMustDownload(dest)){
            Util.logDebug("le fichier existe");
            viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(dest));
        } else
        {
            Util.logDebug("Le fichier "+dest+" n'existe pas");
            new MyImageLoader().execute(new ParamHolder(viewHolder.img, dest));
        }

        //viewHolder.starImag.set(current.starID); changer l'image du coeur
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


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        // TextView discription;
        ImageView img;
        ImageView starImag;
        TextView  txtView;
        boolean fav;

        public ViewHolder(View itemView) {
            super(itemView);
            // discription = (TextView) itemView.findViewById(R.id.text_fav);
            img = (ImageView) itemView.findViewById(R.id.image_view);
            starImag = (ImageView) itemView.findViewById(R.id.star_image);
            txtView = (TextView) itemView.findViewById(R.id.promo_name);
            img.setOnClickListener(this);
            starImag.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view == img) {
                Intent intent = new Intent(context, PromoDetailActivity.class);
                view.getContext().startActivity(intent);
            }
            if(view == starImag)
            {
                if(fav == false) {
                    starImag.setBackgroundResource(R.drawable.ic_fav_select);
                    Toast.makeText(context, "added to favorie",
                            Toast.LENGTH_SHORT).show();
                    fav = true;
                }
                else if(fav == true) {
                    starImag.setBackgroundResource(R.drawable.ic_fav_unselect);
                    Toast.makeText(context, "removed from favorie",
                            Toast.LENGTH_SHORT).show();
                    fav = false;
                }
            }
        }
    }
}