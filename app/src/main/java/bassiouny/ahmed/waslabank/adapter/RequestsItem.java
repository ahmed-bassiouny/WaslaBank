package bassiouny.ahmed.waslabank.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * Created by bassiouny on 03/04/18.
 */

public class RequestsItem extends RecyclerView.Adapter<RequestsItem.MyViewHolder> {

    private List<TripDetails> list;
    private ItemClickInterface anInterface;

    public RequestsItem(Fragment fragment) {
        anInterface = (ItemClickInterface) fragment;
        list = new ArrayList<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvUserName;
        private RatingBar rating;
        private TextView tvLocationFrom;
        private TextView tvLocationTo;

        public MyViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(R.id.tv_time);
            tvUserName = view.findViewById(R.id.tv_user_name);
            rating = view.findViewById(R.id.rating);
            tvLocationFrom = view.findViewById(R.id.tv_location_from);
            tvLocationTo = view.findViewById(R.id.tv_location_to);
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
                .inflate(R.layout.item_request, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TripDetails item = list.get(position);
        holder.tvLocationFrom.setText(item.getStartPointText());
        holder.tvLocationTo.setText(item.getEndPointText());
        holder.tvUserName.setText(item.getDriver().getName());
        holder.tvTime.setText(item.getTime());
        holder.rating.setRating(item.getDriver().getUserDetails().getTotalRate());

    }

    public void setList(List<TripDetails> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
