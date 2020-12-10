package com.example.firebasenotificationpushnotification.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebasenotificationpushnotification.R;
import com.example.firebasenotificationpushnotification.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

public class NotificationFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_notification, container, false);
        // Inflate the layout for this fragment

        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}