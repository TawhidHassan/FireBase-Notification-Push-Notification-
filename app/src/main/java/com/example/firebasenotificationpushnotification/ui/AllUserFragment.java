package com.example.firebasenotificationpushnotification.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebasenotificationpushnotification.R;
import com.example.firebasenotificationpushnotification.adapter.AllUserAdapter;
import com.example.firebasenotificationpushnotification.model.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AllUserFragment extends Fragment {

    View view;
    private RecyclerView alluser;
    private AllUserAdapter adapter;

    public AllUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_all_user, container, false);
        // Inflate the layout for this fragment
        alluser = view.findViewById(R.id.all_user);
        alluser.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), User.class)
                        .build();

        adapter = new AllUserAdapter(options);

        alluser.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}