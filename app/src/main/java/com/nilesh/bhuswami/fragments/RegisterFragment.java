package com.nilesh.bhuswami.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.activities.FragmentChangeListener;


public class RegisterFragment extends Fragment {

    TextInputEditText name,email,passw,mobile;
    Button signup;
    TextView txtlgn;


    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View v = inflater.inflate(R.layout.fragment_register, container, false);


        name = v.findViewById(R.id.editTextName);
        email = v.findViewById(R.id.editTextEmail);
        mobile = v.findViewById(R.id.editTextMobile);
        passw = v.findViewById(R.id.editTextPassword);
        txtlgn = v.findViewById(R.id.logtxt);
        signup = v.findViewById(R.id.cirSignUpButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showOtherFragment();

            }
        });






        return  v;
    }
    public void showOtherFragment()
    {
        Fragment fr=new LoginFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}