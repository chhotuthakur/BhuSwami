package com.nilesh.bhuswami.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.activities.MainActivity;

import java.util.Arrays;
import java.util.List;


public class LoginfbFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;

    // declaring a const int value which we
    // will be using in Firebase auth.
    public static final int RC_SIGN_IN = 1;

    // creating an auth listener for our Firebase auth
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    // below is the list which we have created in which
    // we can add the authentication which we have to
    // display inside our app.
    List<AuthUI.IdpConfig> providers = Arrays.asList(

            // below is the line for adding
            // email and password authentication.
            new AuthUI.IdpConfig.EmailBuilder().build(),

            // below line is used for adding google
            // authentication builder in our app.
            new AuthUI.IdpConfig.GoogleBuilder().build(),

            // below line is used for adding phone
            // authentication builder in our app.
            new AuthUI.IdpConfig.PhoneBuilder().build());


    public LoginfbFragment() {
        // Required empty public constructor
    }

    public static LoginfbFragment newInstance(String param1, String param2) {
        LoginfbFragment fragment = new LoginfbFragment();
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
        View v = inflater.inflate(R.layout.fragment_loginfb, container, false);

        // below line is for getting instance
        // for our firebase auth
        mFirebaseAuth = FirebaseAuth.getInstance();

        // below line is used for calling auth listener
        // for our Firebase authentication.
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                // we are calling method for on authentication state changed.
                // below line is used for getting current user which is
                // authenticated previously.
                FirebaseUser user = firebaseAuth.getCurrentUser();

                // checking if the user
                // is null or not.
                if (user != null) {
                    // if the user is already authenticated then we will
                    // redirect our user to next screen which is our home screen.
                    // we are redirecting to new screen via an intent.
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                    // we are calling finish method to kill or
                    // mainactivity which is displaying our login ui.
                    getActivity().finish();
                } else {
                    // this method is called when our
                    // user is not authenticated previously.
                    startActivityForResult(
                            // below line is used for getting
                            // our authentication instance.
                            AuthUI.getInstance()
                                    // below line is used to
                                    // create our sign in intent
                                    .createSignInIntentBuilder()

                                    // below line is used for adding smart
                                    // lock for our authentication.
                                    // smart lock is used to check if the user
                                    // is authentication through different devices.
                                    // currently we are disabling it.
                                    .setIsSmartLockEnabled(false)

                                    // we are adding different login providers which
                                    // we have mentioned above in our list.
                                    // we can add more providers according to our
                                    // requirement which are available in firebase.
                                    .setAvailableProviders(providers)

                                    // below line is for customizing our theme for
                                    // login screen and set logo method is used for
                                    // adding logo for our login page.
                                    .setLogo(R.drawable.logo_hdpi).setTheme(R.style.loginHeader)

                                    // after setting our theme and logo
                                    // we are calling a build() method
                                    // to build our login screen.
                                    .build(),
                            // and lastly we are passing our const
                            // integer which is declared above.
                            RC_SIGN_IN
                    );
                }
            }
        };




        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        // we are calling our auth
        // listener method on app resume.
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        // here we are calling remove auth
        // listener method on stop.
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}