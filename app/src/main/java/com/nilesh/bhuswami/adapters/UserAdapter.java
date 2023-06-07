package com.nilesh.bhuswami.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.models.Users;

public class UserAdapter extends FirebaseRecyclerAdapter<Users,UserAdapter.userViewHolder> {

    DatabaseReference profileDb = FirebaseDatabase.getInstance().getReference().child("Users");





    public UserAdapter(@NonNull FirebaseRecyclerOptions<Users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserAdapter.userViewHolder holder, int position, @NonNull Users model) {


    holder.name.setText(model.getName());
    holder.mail.setText(model.getEmail());
    holder.phone.setText(model.getPhone());
    }

    @NonNull
    @Override
    public UserAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile, parent, false);
        return new userViewHolder(view);

    }

    public class userViewHolder extends RecyclerView.ViewHolder {


TextView name ,mail,phone;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.name_txt);
            mail = itemView.findViewById(R.id.email_txt);
            phone = itemView.findViewById(R.id.phone_txt);
        }
    }
}
