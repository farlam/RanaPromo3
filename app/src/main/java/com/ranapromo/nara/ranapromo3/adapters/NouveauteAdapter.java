package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.ui.PromoDetailActivity;
import com.ranapromo.nara.ranapromo3.R;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the nouveauté fragment in the last tab
 */
public class NouveauteAdapter extends RecyclerView.Adapter<NouveauteAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Marque> data = Collections.emptyList();

    public NouveauteAdapter(Context context, List<Marque> data){
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public NouveauteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view =  inflater.inflate(R.layout.nouveaute_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NouveauteAdapter.ViewHolder viewHolder, int position) {
        Marque current = data.get(position);
        //  viewHolder.discription.setText(current.discription);
        viewHolder.img.setBackgroundResource(current.image_ID);
        viewHolder.fav = current.favorite;
        //viewHolder.starImag.set(current.starID); changer l'image du coeur
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        // TextView discription;
        ImageView img;
        ImageView starImag;
        int fav;

        public ViewHolder(View itemView) {
            super(itemView);
            // discription = (TextView) itemView.findViewById(R.id.text_fav);
            img = (ImageView) itemView.findViewById(R.id.image_view);
            starImag = (ImageView) itemView.findViewById(R.id.star_image);

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
                if(fav == 0) {
                    starImag.setBackgroundResource(R.drawable.ic_fav_select);
                    Toast.makeText(context, "added to favorie",
                            Toast.LENGTH_SHORT).show();
                    fav = 1;
                }
                else if(fav == 1) {
                    starImag.setBackgroundResource(R.drawable.ic_fav_unselect);
                    Toast.makeText(context, "removed from favorie",
                            Toast.LENGTH_SHORT).show();
                    fav = 0;
                }
            }
        }
    }
}