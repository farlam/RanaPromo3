package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;



import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for the recycler grid layout for when to add "une marque a la liste"
 */
public class MarqueRecyclerAdapter extends RecyclerView.Adapter<MarqueRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Marque> data = Collections.emptyList();


    public MarqueRecyclerAdapter(Context context, List<Marque> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }


    public void add(Marque marque){
        data.add(0,marque);
        notifyItemInserted(0);
    }

    @Override
    public MarqueRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.marque_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MarqueRecyclerAdapter.ViewHolder viewHolder, int position) {
        Marque current = data.get(position);
        String dest = context.getCacheDir() + "/"+current.getMarNom()+".jpg";
        if(Util.isMustDownload(dest)){
            Util.logDebug("le fichier de marque existe");
            viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(dest));
        } else
        {
            Util.logDebug("Le fichier "+dest+" n'existe pas");
            new MyImageLoader().execute(new ParamHolder(viewHolder.img, dest));
        }
        //viewHolder.img.setBackgroundResource(current.imageID);
        viewHolder.fav = current.isFavorite();
        viewHolder.marqueId = current.getMarNom();

        if (viewHolder.fav) {
            viewHolder.img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor));
            viewHolder.fav = !viewHolder.fav;
        } else {
            viewHolder.img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            viewHolder.fav = !viewHolder.fav;
        }

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView img_checked;
        boolean fav;
        String marqueId;


        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.marqueview);
            img_checked = (ImageView) itemView.findViewById(R.id.check_marque);
            img.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            DataBaseHelper da = new DataBaseHelper(context);
            da.open();
            da.setFavoritMarque(marqueId);

            da.close();
            if (fav) {
                img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor));
                fav = !fav;
                Util.logError("Set marque with id "+marqueId+" as favorit");
            } else {
                img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                Util.logError("Set marque with id " + marqueId + " as not favorit");
                fav = !fav;
            }
        }
    }
}