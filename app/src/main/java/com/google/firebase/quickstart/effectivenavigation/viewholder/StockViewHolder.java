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
    public final TextView percentage;
    public final TextView itemName;

    public StockViewHolder(View itemView) {
        super(itemView);
        this.image = (ImageView) itemView.findViewById(R.id.item_stcok$item_image);
        this.percentage = (TextView) itemView.findViewById(R.id.item_stock$percentage);
        this.itemName = (TextView) itemView.findViewById(R.id.item_stock$item_name);
    }

}
