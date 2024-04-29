package com.rafael.endpointrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Users> users;

    private Context context;

    public RecyclerViewAdapter(List<Users> users, Context context){
        this.users = users;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        private   TextView tvId;

        private   TextView tvName;

      private   TextView tvUserName;

      private TextView tvEmail;

      private TextView tvPhone;
      private    TextView tvWebsite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvId = itemView.findViewById(R.id.tv_id);

            tvName = itemView.findViewById(R.id.tv_name);

            tvUserName  = itemView.findViewById(R.id.tv_username);

            tvEmail = itemView.findViewById(R.id.tv_email);

            tvPhone = itemView.findViewById(R.id.tv_phone);

            tvWebsite = itemView.findViewById(R.id.tv_website);

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvId.setText(users.get(position).getId() + "");
        holder.tvName.setText(users.get(position).getName());
        holder.tvUserName.setText(users.get(position).getUsername());
        holder.tvEmail.setText(users.get(position).getEmail());
        holder.tvWebsite.setText(users.get(position).getWebsite());
        holder.tvPhone.setText(users.get(position).getPhone());

    }

    @Override
    public int getItemCount() {

        return users.size();
    }









}
