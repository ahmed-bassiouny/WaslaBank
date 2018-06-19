package com.waslla_bank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.waslla_bank.interfaces.ItemClickInterface;
import com.waslla_bank.model.UserInTrip;
import com.waslla_bank.model.UserInTripFirebase;
import com.waslla_bank.utils.MyGlideApp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class UserInTripItem extends RecyclerView.Adapter<UserInTripItem.MyViewHolder> {

    private Activity activity;
    private List<UserInTrip> users;
    private ItemClickInterface itemClickInterface;

    public UserInTripItem(Activity activity, List<UserInTrip> users) {
        this.activity = activity;
        this.users = users;
        itemClickInterface = (ItemClickInterface) activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvUserName;
        private TextView joinLeave;
        private ViewStub viewStub;

        public MyViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(com.waslla_bank.R.id.iv_avatar);
            tvUserName = view.findViewById(com.waslla_bank.R.id.tv_user_name);
            joinLeave = view.findViewById(com.waslla_bank.R.id.join_leave);
            viewStub = view.findViewById(com.waslla_bank.R.id.view_stub_progress);
            joinLeave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (users.size() == 0)
                        return;
                    itemClickInterface.getItem(users.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.waslla_bank.R.layout.item_user_in_trip, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserInTrip item = users.get(position);
        holder.tvUserName.setText(item.getUserName());
        MyGlideApp.setImage(activity, holder.ivAvatar, item.getUserImage());
        if (item.isLoading()) {
            holder.viewStub.setVisibility(View.VISIBLE);
            holder.joinLeave.setVisibility(View.INVISIBLE);
        } else {
            holder.viewStub.setVisibility(View.GONE);
            holder.joinLeave.setVisibility(View.VISIBLE);
            if (item.getIsEntered()) {
                holder.joinLeave.setText(activity.getResources().getString(com.waslla_bank.R.string.arrive));
                holder.joinLeave.setTextColor(activity.getResources().getColor(com.waslla_bank.R.color.red));
            } else {
                holder.joinLeave.setText(activity.getResources().getString(com.waslla_bank.R.string.join));
                holder.joinLeave.setTextColor(activity.getResources().getColor(com.waslla_bank.R.color.green));
            }
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void loading(int position, boolean isLoading) {
        if (isLoading) {
            UserInTrip user = users.get(position);
            user.setLoading(true);
            users.set(position, user);
        } else {
            UserInTrip user = users.get(position);
            user.setLoading(false);
            users.set(position, user);
        }
        notifyItemChanged(position);
    }

    public void joinedUser(int position){
        UserInTrip item = users.get(position);
        item.setIsEntered();
        users.set(position,item);
        notifyItemChanged(position);
    }
    public void removeItem(int position){
        users.remove(position);
        notifyItemRemoved(position);
    }
}
