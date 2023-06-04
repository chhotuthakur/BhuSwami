package com.nilesh.bhuswami.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.activities.FragmentChangeListener;
import com.nilesh.bhuswami.models.Users;


public class RegisterFragment extends Fragment {

    TextInputEditText name,email,passw,mobile;
    Button signup;
    TextView txtlgn;
    FirebaseAuth mAuth;
    String id,rname,remail,rpassw,rmobile;
    FirebaseDatabase mData;
    DatabaseReference databaseReference;


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

                rname = name.getText().toString();
                rmobile = mobile.getText().toString();
                remail = email.getText().toString();
                rpassw = passw.getText().toString();
                id = rmobile;
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(remail,rpassw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        Users users = new Users();

                        databaseReference.child("Users").child(id).child("name").setValue(rname);
                        databaseReference.child("Users").child(id).child("email").setValue(remail);
                        databaseReference.child("Users").child(id).child("phone").setValue(rmobile);


//                        databaseReference.child("Customer").child(id).child("address").setValue(locate);
//                        databaseReference.child("Customer").child(id).child("type").setValue(type);
//                    databaseReference.child("Customer").child(id).child("type").setValue(type);


                        Toast.makeText(getContext(),"Registered Succesfully! Please Login",Toast.LENGTH_LONG).show();
                        Fragment fragment = new LoginFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.logregholder,fragment,fragment.toString())
                                .addToBackStack(null)
                                .commit();


                    }
                });

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