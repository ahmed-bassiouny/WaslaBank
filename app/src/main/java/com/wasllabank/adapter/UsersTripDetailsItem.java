package com.wasllabank.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.wasllabank.utils.MyGlideApp;

import java.util.List;

import com.wasllabank.interfaces.UserTripDetailsInterface;
import com.wasllabank.model.UserInTrip;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class UsersTripDetailsItem extends RecyclerView.Adapter<UsersTripDetailsItem.MyViewHolder> {

    private Fragment fragment;
    private UserTripDetailsInterface anInterface;
    private List<UserInTrip> users;

    public UsersTripDetailsItem(Fragment fragment, List<UserInTrip> users) {
        this.fragment = fragment;
        this.anInterface = (UserTripDetailsInterface) fragment;
        this.users = users;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private CircleImageView ivAvatar;
        private TextView tvUserName;
        private TextView tvUserPhone;
        private TextView tvAccept;
        private TextView tvReject;
        private ViewStub viewStubProgress;

        public MyViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(com.wasllabank.R.id.iv_avatar);
            tvUserName = view.findViewById(com.wasllabank.R.id.tv_user_name);
            tvUserPhone = view.findViewById(com.wasllabank.R.id.tv_user_phone);
            tvAccept = view.findViewById(com.wasllabank.R.id.tv_accept);
            tvReject = view.findViewById(com.wasllabank.R.id.tv_reject);
            viewStubProgress = view.findViewById(com.wasllabank.R.id.view_stub_progress);
            tvAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    anInterface.accept(getAdapterPosition());
                }
            });
            tvReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    anInterface.reject(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    anInterface.viewProfile(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.wasllabank.R.layout.item_users_trip_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (fragment.getContext() == null) {
            notifyItemRemoved(position);
            return;
        }
        UserInTrip userInTrip = users.get(position);
        holder.tvUserName.setText(userInTrip.getUserName());
        holder.tvUserPhone.setText(userInTrip.getUserPhone());
        MyGlideApp.setImageCenterInside(fragment.getContext(), holder.ivAvatar, userInTrip.getUserImage());
        if (userInTrip.isLoading()) {
            holder.tvReject.setVisibility(View.INVISIBLE);
            holder.tvAccept.setVisibility(View.INVISIBLE);
            holder.viewStubProgress.setVisibility(View.VISIBLE);
        } else if (userInTrip.isAccepted()) {
            // user in trip
            holder.viewStubProgress.setVisibility(View.GONE);
            holder.tvAccept.setVisibility(View.INVISIBLE);
            holder.tvReject.setText(fragment.getContext().getString(com.wasllabank.R.string.accepted));
            holder.tvReject.setTextColor(fragment.getContext().getResources().getColor(com.wasllabank.R.color.green));
        } else {
            // user waiting driver reply
            holder.viewStubProgress.setVisibility(View.GONE);
            holder.tvReject.setVisibility(View.VISIBLE);
            holder.tvAccept.setText(fragment.getContext().getString(com.wasllabank.R.string.accept));
            holder.tvAccept.setTextColor(fragment.getContext().getResources().getColor(com.wasllabank.R.color.colorPrimary));
        }
    }

    public void changeItem(UserInTrip user, int position) {
        if (users.size() == 0)
            return;
        this.users.set(position, user);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        if (users.size() == 0)
            return;
        this.users.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
