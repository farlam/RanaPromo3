package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.ui.MarqueDetail;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the store activity from the Navigation drawer
 */
public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<HomeData> data = Collections.emptyList();

    public StoreRecyclerAdapter(Context context, List<HomeData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public StoreRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.store_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreRecyclerAdapter.ViewHolder viewHolder, int position) {
        HomeData current = data.get(position);

        viewHolder.img.setBackgroundResource(current.imageID);
        viewHolder.fav = current.fav;

        if (viewHolder.fav) {
            viewHolder.heart_view.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.fav = !viewHolder.fav;
        } else {
            viewHolder.heart_view.setImageResource(R.drawable.ic_favorite_white_24dp);
            viewHolder.fav = !viewHolder.fav;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView heart_view;
        boolean fav;


        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imageButton2);
            heart_view = (ImageView) itemView.findViewById(R.id.heart_image);
            heart_view.setOnClickListener(this);
            img.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(view == heart_view){
                if (fav) {
                    heart_view.setImageResource(R.drawable.ic_favorite_black_24dp);

                    fav = !fav;
                } else {
                    heart_view.setImageResource(R.drawable.ic_favorite_white_24dp);
                    fav = !fav;
                }
            }
            if(view == img){
                Intent i = new Intent(context,MarqueDetail.class);
                view.getContext().startActivity(i);
            }

        }
    }
}