package com.wasllabank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasllabank.interfaces.ItemClickInterface;

/**
 * Created by bassiouny on 06/04/18.
 */

public class HomeMenuItem extends RecyclerView.Adapter<HomeMenuItem.GridViewHolder> {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;
    private LayoutInflater mLayoutInflater;
    private ItemClickInterface clickInterface;

    public HomeMenuItem(Context mContext, String[] gridViewString, int[] gridViewImageId) {
        clickInterface = (ItemClickInterface) mContext;
        this.gridViewString = gridViewString;
        this.gridViewImageId = gridViewImageId;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(com.wasllabank.R.layout.item_home_menu, parent, false);
        return new GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.tvMenu.setText(gridViewString[position]);
        holder.ivMenu.setImageResource(gridViewImageId[position]);
    }

    @Override
    public int getItemCount() {
        return gridViewImageId.length;
    }

    protected class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenu;
        ImageView ivMenu;

        public GridViewHolder(View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(com.wasllabank.R.id.tv_menu);
            ivMenu = itemView.findViewById(com.wasllabank.R.id.iv_menu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.getItem(null,getAdapterPosition());
                }
            });
        }

    }


}
