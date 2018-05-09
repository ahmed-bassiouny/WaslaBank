package bassiouny.ahmed.wasllabank.adapter;

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

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.wasllabank.model.UserInTripFirebase;
import bassiouny.ahmed.wasllabank.utils.MyGlideApp;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 03/04/18.
 */

public class UserInTripItem extends RecyclerView.Adapter<UserInTripItem.MyViewHolder> {

    private Context context;
    private List<UserInTripFirebase> users;
    private ItemClickInterface itemClickInterface;

    public UserInTripItem(Context context) {
        this.context = context;
        users = new ArrayList<>();
        itemClickInterface = (ItemClickInterface) context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvUserName;
        private TextView joinLeave;
        private ViewStub viewStub;

        public MyViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvUserName = view.findViewById(R.id.tv_user_name);
            joinLeave = view.findViewById(R.id.join_leave);
            viewStub = view.findViewById(R.id.view_stub_progress);
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
                .inflate(R.layout.item_user_in_trip, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserInTripFirebase item = users.get(position);
        holder.tvUserName.setText(item.getName());
        MyGlideApp.setImage(context, holder.ivAvatar, item.getImage());
        if (item.isLoading) {
            holder.viewStub.setVisibility(View.VISIBLE);
            holder.joinLeave.setVisibility(View.INVISIBLE);
        } else {
            holder.viewStub.setVisibility(View.GONE);
            holder.joinLeave.setVisibility(View.VISIBLE);
            if (item.isJoined()) {
                holder.joinLeave.setText(context.getResources().getString(R.string.arrive));
                holder.joinLeave.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                holder.joinLeave.setText(context.getResources().getString(R.string.join));
                holder.joinLeave.setTextColor(context.getResources().getColor(R.color.green));
            }
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void loading(int position, boolean isLoading) {
        if (isLoading) {
            UserInTripFirebase user = users.get(position);
            user.isLoading = true;
            users.set(position, user);
        } else {
            UserInTripFirebase user = users.get(position);
            user.isLoading = false;
            users.set(position, user);
        }
        notifyItemChanged(position);
    }

    public void clearList() {
        this.users.clear();
    }

    public void addUser(UserInTripFirebase user) {
        this.users.add(user);

        notifyDataSetChanged();
    }
}
