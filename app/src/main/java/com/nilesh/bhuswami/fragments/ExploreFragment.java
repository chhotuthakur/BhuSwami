package com.nilesh.bhuswami.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.adapters.PlotAdapter;
import com.nilesh.bhuswami.models.Plots;
import com.nilesh.bhuswami.models.Users;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    RecyclerView recyclerView;
    PlotAdapter adapter;
    Plots plots;
    DatabaseReference mbase;

    FirebaseAuth mauth = FirebaseAuth.getInstance();
    FirebaseUser user = mauth.getCurrentUser();
    String loggeduser = user.getEmail().toString();


    public ExploreFragment() {
        // Required empty public constructor
    }


    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);


        recyclerView = v.findViewById(R.id.explore_recy);






        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user  = auth.getCurrentUser();
        String author = user.getEmail().toString();

        Query query = reference.child("plots").orderByChild("author").equalTo(author);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {



                        mbase = FirebaseDatabase.getInstance().getReference().child("plots");


                        // To display the Recycler view linearly
                        recyclerView.setLayoutManager(
                                new LinearLayoutManager(getContext()));

                        // It is a class provide by the FirebaseUI to make a
                        // query in the database to fetch appropriate data
                        FirebaseRecyclerOptions<Plots> options =
                                new FirebaseRecyclerOptions.Builder<Plots>()
                                        .setQuery(mbase.orderByChild("author").equalTo(loggeduser), Plots.class)
                                        .build();
                        // Connecting object of required Adapter class to
                        // the Adapter class itself
                        adapter = new PlotAdapter(options);


                        // Connecting Adapter class with the Recycler view*/
                        recyclerView.setAdapter(adapter);
                        adapter.startListening();

                    }
                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return v;
    }
}