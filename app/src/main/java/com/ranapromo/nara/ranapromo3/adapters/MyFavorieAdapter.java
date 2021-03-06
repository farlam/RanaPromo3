package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.ui.PromoDetailActivity;
import com.ranapromo.nara.ranapromo3.R;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for My favorie activity from the Navigation drawer   NOTE: for now it's basically the same as MyPromoAdapter
 */

public class MyFavorieAdapter extends RecyclerView.Adapter<MyFavorieAdapter.ViewHolder> {

    private  LayoutInflater inflater;
    private  Context context;
    List<Promotion> dummyData = Collections.emptyList();


    public MyFavorieAdapter(Context context,List<Promotion> data){
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.dummyData = data;

    }

    @Override
    public MyFavorieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view =  inflater.inflate(R.layout.promos_row, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyFavorieAdapter.ViewHolder viewHolder, int position) {

        Promotion current = dummyData.get(position);

        viewHolder.promoName.setText(current.getProTitre());
        viewHolder.reduc.setText(current.getProTauxRed().toString());
        viewHolder.oldPrice.setText(current.getProPrix().toString());
        viewHolder.newPrice.setText(current.getNewPrice().toString());
        viewHolder.countDown.setText(current.getTimeLeft().toString());
        //viewHolder.img.setBackgroundResource(current.image_ID);
        //viewHolder.fav = current.favorite;

        viewHolder.intentPromo = new Promotion(current.getProId(),current.getProPrix(),current.getNewPrice(),current.getProPrix(),current.getProDes(),current.getProTauxRed(),current.getTimeLeft(),current.getProDes());

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
        int fav;
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
                if(fav == 0) {

                    starImag.setBackgroundResource(R.drawable.ic_fav_select);
                    fav = 1;

                    Toast.makeText(context, "added to favorie",
                            Toast.LENGTH_SHORT).show();
                }

                else if(fav == 1) {

                    starImag.setBackgroundResource(R.drawable.ic_fav_unselect);
                    fav = 0;

                    Toast.makeText(context, "removed from favorie",
                            Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
