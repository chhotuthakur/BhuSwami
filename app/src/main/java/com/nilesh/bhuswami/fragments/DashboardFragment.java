package com.nilesh.bhuswami.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.adapters.PlotAdapter;
import com.nilesh.bhuswami.models.Plots;


public class DashboardFragment extends Fragment {


    private RecyclerView plotList;

    PlotAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    public DashboardFragment() {
        // Required empty public constructor
    }


    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        plotList = v.findViewById(R.id.list_plot);


        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference().child("plots");


        // To display the Recycler view linearly
        plotList.setLayoutManager(
                new LinearLayoutManager(getContext()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Plots> options
                = new FirebaseRecyclerOptions.Builder<Plots>()
                .setQuery(mbase, Plots.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new PlotAdapter(options);


        // Connecting Adapter class with the Recycler view*/
        plotList.setAdapter(adapter);
        adapter.startListening();

       

        return v;
    }


//    @Override
//    public void onStart()
//    {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    // Function to tell the app to stop getting
//    // data from database on stopping of the activity
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        adapter.stopListening();
//    }


}