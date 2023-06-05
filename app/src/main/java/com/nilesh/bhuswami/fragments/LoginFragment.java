package com.nilesh.bhuswami.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nilesh.bhuswami.activities.MainActivity;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.activities.FragmentChangeListener;


public class LoginFragment extends Fragment {

    TextInputEditText mail,pass;
    Button login;
    TextView txtsgn;
    FirebaseAuth mAuth;
    String lmail,lpass;


    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View v =  inflater.inflate(R.layout.fragment_login, container, false);

        mail = v.findViewById(R.id.leditTextEmail);
        pass = v.findViewById(R.id.leditTextPassword);
        txtsgn = v.findViewById(R.id.signup_txt);
        login = v.findViewById(R.id.cirLoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lmail = mail.getText().toString();
                lpass = pass.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                if (lmail != null && lpass != null) {

            mAuth.signInWithEmailAndPassword(lmail,lpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                 public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            });
                }

            }
        });
        txtsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherFragment();

            }
        });








        return v;
    }
    public void showOtherFragment()
    {
        Fragment fr=new RegisterFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}