package com.inspiredcoda.rxjavaretrofitandpaginglibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inspiredcoda.rxjavaretrofitandpaginglibrary.R;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private List<User> users = new ArrayList<>();

    public CustomAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewer = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout, parent, false);
        return new CustomViewHolder(viewer);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.title.setText(users.get(position).getTitle());
        holder.body.setText(users.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView title, body;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

        }
    }
}
