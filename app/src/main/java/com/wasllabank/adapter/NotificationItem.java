package com.wasllabank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasllabank.model.Notification;
import com.wasllabank.utils.MyGlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class NotificationItem extends RecyclerView.Adapter<NotificationItem.MyViewHolder> {

    private List<Notification> notifications;
    private Context context;

    public NotificationItem(Context context, List<Notification> notifications) {
        this.notifications = notifications;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvBody;
        private TextView tvTime;


        public MyViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(com.wasllabank.R.id.iv_avatar);
            tvName = view.findViewById(com.wasllabank.R.id.tv_name);
            tvBody = view.findViewById(com.wasllabank.R.id.tv_body);
            tvTime = view.findViewById(com.wasllabank.R.id.tv_time);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.wasllabank.R.layout.item_notification, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notification item = notifications.get(position);
        MyGlideApp.setImage(context,holder.ivAvatar,item.getImage());
        holder.tvName.setText(item.getName());
        holder.tvTime.setText(item.getTime());
        holder.tvBody.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

}
