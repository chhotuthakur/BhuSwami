package com.nilesh.bhuswami.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nilesh.bhuswami.R;
import com.nilesh.bhuswami.fragments.AddFragment;
import com.nilesh.bhuswami.fragments.DashboardFragment;
import com.nilesh.bhuswami.fragments.ExploreFragment;
import com.nilesh.bhuswami.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FirebaseAuth mAuth ;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.home_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment = new DashboardFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_container,fragment);
        transaction.addToBackStack(fragment.toString());
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        // Find our drawer view
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.home_nav_view);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView nvDrawer) {

        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

    }

    private void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;
        Class fragmentClass = null;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.dashboard) {
            fragmentClass = DashboardFragment.class;
        } else if (itemId == R.id.explore) {
            fragmentClass = ExploreFragment.class;
        }
        else if (itemId == R.id.profile) {
            fragmentClass = ProfileFragment.class;
        }
        else if (itemId == R.id.adddata) {
            fragmentClass = AddFragment.class;
        }
             else if (itemId == R.id.logoutmenu) {

                 mAuth = FirebaseAuth.getInstance();
                 user = mAuth.getCurrentUser();
                 mAuth.signOut();
                 if (user == null){
                     Toast.makeText(this, "SignOut Success", Toast.LENGTH_LONG).show();
                     startActivity(new Intent(MainActivity.this,AccountActivity.class));
                     finish();
                 }


//                FirebaseAuth.getInstance().signOut();


        }else {
            fragmentClass = DashboardFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.home_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(false);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();

    }
}