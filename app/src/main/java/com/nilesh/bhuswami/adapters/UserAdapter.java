package com.nilesh.bhuswami.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nilesh.bhuswami.models.Users;

public class UserAdapter extends FirebaseRecyclerAdapter<Users,UserAdapter.userViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<Users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserAdapter.userViewHolder holder, int position, @NonNull Users model) {

    }

    @NonNull
    @Override
    public UserAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class userViewHolder extends RecyclerView.ViewHolder {




        public userViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
