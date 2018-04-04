package com.google.firebase.quickstart.effectivenavigation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.effectivenavigation.R;

/**
 * Created by DELL on 4/3/2018.
 */

public class StockViewHolder extends RecyclerView.ViewHolder {

    public final ImageView image;
    public final TextView time;
    public final TextView metadata;

    public StockViewHolder(View itemView) {
        super(itemView);
        this.image = (ImageView) itemView.findViewById(R.id.item_stcok$item_image);
        this.time = (TextView) itemView.findViewById(R.id.item_stock$percentage);
        this.metadata = (TextView) itemView.findViewById(R.id.item_stock$percentage);
    }

}
