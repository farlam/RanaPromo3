package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ranapromo.nara.ranapromo3.ui.MarqueActivity;
import com.ranapromo.nara.ranapromo3.ui.MarqueDetail;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.Data.HomeData;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the grid layout recycler in the first tab
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    private LayoutInflater inflater;
    private Context context;
    List<HomeData> data = Collections.emptyList();

public HomeRecyclerAdapter(Context context, List<HomeData> data){
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.data = data;
        }
@Override
public int getItemViewType(int position) {
    if (this.data.isEmpty()) {
        return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
    } else {
        return VIEW_TYPE_OBJECT_VIEW;
    }
}
    @Override
public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view= inflater.inflate(R.layout.marque_item_empty, viewGroup, false);

        switch (viewType) {

            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                // return view holder for your placeholder

                view = inflater.inflate(R.layout.marque_item_empty, viewGroup, false);
                break;

            case VIEW_TYPE_OBJECT_VIEW:
                // return view holder for your normal list item

                if (viewType != (data.size() - 1)) {
                    view = inflater.inflate(R.layout.home_marque_item, viewGroup, false);
                } else {
                    view = inflater.inflate(R.layout.marque_item, viewGroup, false);
                }
                break;
        }
        return new ViewHolder(view);
    }


@Override
public void onBindViewHolder(HomeRecyclerAdapter.ViewHolder viewHolder, int position) {

    if(viewHolder.img != null){
            if(position != (data.size())){
                HomeData current = data.get(position);
                viewHolder.discription.setText(current.discription);
                viewHolder.img.setBackgroundResource(current.imageID);
            } else{
                viewHolder.img.setBackgroundResource(R.drawable.ic_add_black);
                viewHolder.mFrameLayout.setVisibility(View.GONE); // set visibilité gone if marque has 0 notification
            }
    }
}

@Override
public int getItemCount() {
        return data.size()+1;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    TextView discription;
    ImageView img;
    FrameLayout mFrameLayout;
    LinearLayout mLinearLayout;


    public ViewHolder(View itemView) {
        super(itemView);
        mLinearLayout = (LinearLayout)itemView.findViewById(R.id.empy_view);
        discription = (TextView) itemView.findViewById(R.id.textnumber);
        img = (ImageView) itemView.findViewById(R.id.marqueview);
        mFrameLayout = (FrameLayout)itemView.findViewById(R.id.notif_layout);
        if(mLinearLayout != null) {
            mLinearLayout.setOnClickListener(this);
        }
        if(img != null) {
            img.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if(getAdapterPosition() == data.size() || data.isEmpty())
        {
            Intent i = new Intent(context,MarqueActivity.class);
            view.getContext().startActivity(i);
        } else {
            Intent intent = new Intent(context,MarqueDetail.class);
            view.getContext().startActivity(intent);
        }

    }
}

}