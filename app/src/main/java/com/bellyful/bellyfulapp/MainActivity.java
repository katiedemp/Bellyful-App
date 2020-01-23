package com.bellyful.bellyfulapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bellyful.bellyfulapp.Freezers.FreezerConfirmFragment;
import com.bellyful.bellyfulapp.Freezers.FreezersUpdateFragment;
import com.bellyful.bellyfulapp.Freezers.FreezerContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FreezersFragment.OnListFragmentInteractionListener, FreezersUpdateFragment.OnListFragmentInteractionListener, FreezerConfirmFragment.OnFragmentInteractionListener {

    private FirebaseAuth mAuth; //Firebase auth
    BottomNavigationView bottomNavigationView; // Bottom navigation bar
    private ArrayList<JobData> newJobList = new ArrayList<>();

    Toolbar mToolbar;
    Fragment ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth

        //Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Send jobData to the fragment
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("newJobList", newJobList);
        NewJobsFragment fragment = new NewJobsFragment();
        fragment.setArguments(bundle);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.new_deliveries);
        //Load starting fragment
        loadFragment(new NewJobsFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_new_jobs:
                    //Send jobData to the fragment
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("newJobList", newJobList);
                    ft = new NewJobsFragment();
                    loadFragment(ft);
                    return true;
                case R.id.action_current_jobs:
                    ft = new CurrentJobsFragment();
                    loadFragment(ft);
                    return true;
                case R.id.action_freezers:
                    ft = new FreezersFragment();
                    loadFragment(ft);
                    return true;
                case R.id.action_user:
                    ft = new UserAccountFragment();
                    loadFragment(ft);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(Fragment fragment) {
        //For making loading fragments easier and cleaner
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void updateUI(FirebaseUser currentUser){
        if(currentUser == null){
            //User not logged in
            //Start login activity
            Intent startLoginIntent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(startLoginIntent);
        }else{
            //User is already logged in
            //Start activity
            Toast.makeText(MainActivity.this, "Logged in",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onListFragmentInteraction(FreezerContent.FreezerItem item) {
        ft = new FreezersUpdateFragment();
        loadFragment(ft);
    }

    @Override
    public void onListFreezersUpdateFragmentInteraction(FreezerContent.FreezerItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
