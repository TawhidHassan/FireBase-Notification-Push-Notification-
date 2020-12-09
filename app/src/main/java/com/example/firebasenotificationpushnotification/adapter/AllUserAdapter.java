package com.example.firebasenotificationpushnotification.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasenotificationpushnotification.R;
import com.example.firebasenotificationpushnotification.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class AllUserAdapter extends FirebaseRecyclerAdapter<User, AllUserAdapter.AllUserViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AllUserAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AllUserViewHolder holder, int position, @NonNull User model) {
        holder.email.setText(model.getEmail());
        holder.name.setText(model.getName());
        Picasso.get().load(model.getImage()).into(holder.image);
    }

    @NonNull
    @Override
    public AllUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new AllUserViewHolder(view);
    }

    class AllUserViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,email;
        public AllUserViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
