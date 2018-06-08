package com.wasllabank.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wasllabank.model.TripDetails;

import java.util.ArrayList;
import java.util.List;

import com.wasllabank.interfaces.ItemClickInterface;
import com.wasllabank.utils.MyGlideApp;

/**
 * Created by bassiouny on 03/04/18.
 */

public class RequestsItem extends RecyclerView.Adapter<RequestsItem.MyViewHolder> {

    private List<TripDetails> list;
    private ItemClickInterface anInterface;
    private Fragment fragment;

    public RequestsItem(Fragment fragment) {
        this.fragment = fragment;
        anInterface = (ItemClickInterface) fragment;
        list = new ArrayList<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvUserName;
        private RatingBar rating;
        private TextView tvLocationFrom;
        private TextView tvLocationTo;
        private ImageView ivMap;
        public MyViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(com.wasllabank.R.id.tv_time);
            tvUserName = view.findViewById(com.wasllabank.R.id.tv_user_name);
            rating = view.findViewById(com.wasllabank.R.id.rating);
            tvLocationFrom = view.findViewById(com.wasllabank.R.id.tv_location_from);
            tvLocationTo = view.findViewById(com.wasllabank.R.id.tv_location_to);
            ivMap = view.findViewById(com.wasllabank.R.id.iv_map);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    anInterface.getItem(list.get(getAdapterPosition()).getId(),getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.wasllabank.R.layout.item_request, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TripDetails item = list.get(position);
        holder.tvLocationFrom.setText("From : "+item.getStartPointText());
        holder.tvLocationTo.setText("To : "+item.getEndPointText());
        holder.tvUserName.setText("Host : "+item.getDriver().getName());
        holder.tvTime.setText(item.getTime());
        holder.rating.setRating(item.getDriver().getRate());
        MyGlideApp.setImageWithoutPlaceholder(fragment.getContext(),holder.ivMap,item.getImage());

    }

    public void setList(List<TripDetails> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addList(List<TripDetails> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
