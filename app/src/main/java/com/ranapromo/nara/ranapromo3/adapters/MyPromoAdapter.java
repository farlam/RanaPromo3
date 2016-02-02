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

import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.ui.PromoDetailActivity;
import com.ranapromo.nara.ranapromo3.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/***
 * Adapter for my promo fragment in the second tab
 */
public class MyPromoAdapter extends RecyclerView.Adapter<MyPromoAdapter.ViewHolder> {

    private  LayoutInflater inflater;
    private  Context context;
    List<Promotion> dummyData = Collections.emptyList();
    DecimalFormat myFormatter = new DecimalFormat("###,###.00 DA");
    DecimalFormat myFormatter2 = new DecimalFormat("###,###.##");
    private String fileName;

    public MyPromoAdapter(Context context,List<Promotion> data){
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.dummyData = data;
    }

    @Override
    public MyPromoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view =  inflater.inflate(R.layout.promos_row, viewGroup, false);
        return new ViewHolder(view);

    }


    public void add(Promotion model){
        dummyData.add(0,model);
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(MyPromoAdapter.ViewHolder viewHolder, int position) {

        Promotion current = dummyData.get(position);

        viewHolder.promoName.setText(current.getProTitre());
        viewHolder.reduc.setText(myFormatter2.format(current.getProTauxRed()));
        viewHolder.oldPrice.setText(myFormatter.format(current.getProPrix()));
        viewHolder.newPrice.setText(myFormatter.format(current.getNewPrice()));
        viewHolder.countDown.setText(calculateLeftTime(current.getProEndDate()));
        //vérifier si l'image existe ou pas
        String dest = context.getCacheDir() + "/" + "PROMO_" +current.getProId()+"_1.jpg";
        if(Util.isMustDownload(dest)){
            Util.logDebug("le fichier existe");
            viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(dest));
        } else
        {
            Util.logDebug("Le fichier "+dest+" n'existe pas");
            new MyImageLoader().execute(new ParamHolder(viewHolder.img, dest));
        }
        viewHolder.fav = current.isFavorite();
        viewHolder.intentPromo = new Promotion(current.getProId(),current.getProPrix(),current.getMarNom(),current.getProTitre(),current.getProTauxRed(),current.getProDes(),current.isFavorite(),current.getProEndDate());
        //viewHolder.intentPromo = new Promotion(current.promoID,current.oldPrice,current.newPrice,current.marqueName,current.promotionName,current.reduction,current.timeLeft,current.description,current.image_ID,current.favorite);

    }

    private String calculateLeftTime(Date proEndDate) {
        if (proEndDate == null) return "~";
        long dif = proEndDate.getTime()-new Date().getTime();
        long day = dif/(1000*60*60*24);
        long left = dif-(day*(1000*60*60*24));
        long hour = left/(1000*60*60);
        left = left-(hour*(1000*60*60));
        long min = left/(1000*60);
        left = left-(min*(1000*60));
        long sec = left/(1000);
        return day+"j "+hour+"h "+min+"m "+sec+"s";
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
        return dummyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView promoName;
        TextView reduc;
        TextView oldPrice;
        TextView newPrice;
        TextView countDown;
        ImageView img;
        ImageView starImag;
        boolean fav;
        Promotion intentPromo;

        public ViewHolder(View itemView) {
            super(itemView);

            promoName = (TextView) itemView.findViewById(R.id.promo_name);
            reduc = (TextView) itemView.findViewById(R.id.reduction);
            oldPrice = (TextView) itemView.findViewById(R.id.old_promo_prix);
            newPrice = (TextView) itemView.findViewById(R.id.new_promo_prix);
            countDown = (TextView) itemView.findViewById(R.id.count_down_text);
            img = (ImageView) itemView.findViewById(R.id.imageButton2);
            starImag = (ImageView) itemView.findViewById(R.id.star_image);

            img.setOnClickListener(this);
            starImag.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            if(view == img) {


                // start the produit activity
                Intent intent = new Intent(context, PromoDetailActivity.class);
                intent.putExtra("promo",intentPromo);
                view.getContext().startActivity(intent);
            }

            if(view == starImag)
            {
                if(fav == false) {

                    starImag.setBackgroundResource(R.drawable.ic_fav_select);
                    fav = true;

                        Toast.makeText(context, "added to favorie",
                            Toast.LENGTH_SHORT).show();
                    }

                else if(fav == true) {

                    starImag.setBackgroundResource(R.drawable.ic_fav_unselect);
                    fav = false;

                        Toast.makeText(context, "removed from favorie",
                                Toast.LENGTH_SHORT).show();

                    }
            }
        }
    }
}

