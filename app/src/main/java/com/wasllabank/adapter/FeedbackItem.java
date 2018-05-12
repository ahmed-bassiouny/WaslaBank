package com.wasllabank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wasllabank.model.Feedback;
import com.wasllabank.utils.MyGlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class FeedbackItem extends RecyclerView.Adapter<FeedbackItem.MyViewHolder> {

    private List<Feedback> feedbackList;
    private Context context;

    public FeedbackItem(Context context,List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvUserName;
        private TextView tvComment;
        private RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(com.wasllabank.R.id.iv_avatar);
            tvUserName = view.findViewById(com.wasllabank.R.id.tv_user_name);
            ratingBar = view.findViewById(com.wasllabank.R.id.rating);
            tvComment = view.findViewById(com.wasllabank.R.id.tv_comment);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.wasllabank.R.layout.item_feedback, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Feedback item = feedbackList.get(position);
        holder.tvComment.setText(item.getComment());
        holder.ratingBar.setRating(item.getRate());
        holder.tvUserName.setText(item.getUserName());
        MyGlideApp.setImageCenterInside(context,holder.ivAvatar,item.getUserImage());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

}
