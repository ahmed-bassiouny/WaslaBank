package bassiouny.ahmed.waslabank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.interfaces.UserTripDetailsInterface;
import bassiouny.ahmed.waslabank.model.UserInTripFirebase;
import bassiouny.ahmed.waslabank.utils.MyGlideApp;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class UsersTripDetailsItem extends RecyclerView.Adapter<UsersTripDetailsItem.MyViewHolder> {

    private Context context;
    private UserTripDetailsInterface anInterface;

    public UsersTripDetailsItem(Context context) {
        this.context = context;
        this.anInterface = (UserTripDetailsInterface) context;
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
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvUserName = view.findViewById(R.id.tv_user_name);
            tvUserPhone = view.findViewById(R.id.tv_user_phone);
            tvAccept = view.findViewById(R.id.tv_accept);
            tvReject = view.findViewById(R.id.tv_reject);
            viewStubProgress = view.findViewById(R.id.view_stub_progress);
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
                .inflate(R.layout.item_user_in_trip, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}