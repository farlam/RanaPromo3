package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.R;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the "ou acheter" activity's recyclerview
 */
public class StoreLocationRecyclerAdapter extends RecyclerView.Adapter<StoreLocationRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Store> data = Collections.emptyList();

    public StoreLocationRecyclerAdapter(Context context, List<Store> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public StoreLocationRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.map_recycler_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreLocationRecyclerAdapter.ViewHolder viewHolder, int position) {
        Store current = data.get(position);

        viewHolder.img.setBackgroundResource(current.image_ID);
        viewHolder.lieu.setText(current.lieu);
        viewHolder.distance.setText(current.distance);
        viewHolder.name.setText(current.name);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView lieu;
        TextView name;
        TextView distance;



        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.logo);
            lieu = (TextView) itemView.findViewById(R.id.lieu_text);
            name = (TextView) itemView.findViewById(R.id.name_text);
            distance = (TextView) itemView.findViewById(R.id.distance_text);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //open google map

        }
    }
}