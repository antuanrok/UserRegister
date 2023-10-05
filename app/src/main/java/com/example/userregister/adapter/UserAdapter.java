package com.example.userregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregister.R;
import com.example.userregister.pojo.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public List<User> getUsers() {
        return users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewLastName.setText(user.getLastname());
        holder.textViewAge.setText(String.valueOf(user.getAge()));
        holder.textViewSex.setText(user.getSex());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewAge;
        private TextView textViewSex;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewSex = itemView.findViewById(R.id.textViewSex);
        }
    }
}
