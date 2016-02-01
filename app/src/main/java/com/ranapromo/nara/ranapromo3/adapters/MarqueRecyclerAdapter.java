package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ranapromo.nara.ranapromo3.Data.HomeData;
import com.ranapromo.nara.ranapromo3.R;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the recycler grid layout for when to add "une marque a la liste"
 */
public class MarqueRecyclerAdapter extends RecyclerView.Adapter<MarqueRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<HomeData> data = Collections.emptyList();

    public MarqueRecyclerAdapter(Context context, List<HomeData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MarqueRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.marque_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MarqueRecyclerAdapter.ViewHolder viewHolder, int position) {
        HomeData current = data.get(position);

        viewHolder.img.setBackgroundResource(current.imageID);
        viewHolder.fav = current.fav;

        if (viewHolder.fav) {
            viewHolder.img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor));
            viewHolder.fav = !viewHolder.fav;
        } else {
            viewHolder.img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            viewHolder.fav = !viewHolder.fav;
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


        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.marqueview);
            img_checked = (ImageView) itemView.findViewById(R.id.check_marque);
            img.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (fav) {
                img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor));
                fav = !fav;
            } else {
                img_checked.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                fav = !fav;
            }
        }
    }
}