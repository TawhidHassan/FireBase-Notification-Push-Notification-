package com.example.firebasenotificationpushnotification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebasenotificationpushnotification.adapter.NotificationAdapter;
import com.example.firebasenotificationpushnotification.model.Notification;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class CloudFunctionFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;

    public CloudFunctionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cloud_function, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Notification> options =
                new FirebaseRecyclerOptions.Builder<Notification>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notification")
                                .child(FirebaseAuth.getInstance().getUid()).child("CloudFuncation"), Notification.class)
                        .build();

        adapter = new NotificationAdapter(options);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
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