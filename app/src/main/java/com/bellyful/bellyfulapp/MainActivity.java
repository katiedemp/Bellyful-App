package com.bellyful.bellyfulapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
//import com.bellyful.bellyfulapp.FreezersUI.FreezerConfirmFragment;
//import com.bellyful.bellyfulapp.FreezersUI.FreezersUpdateFragment;
import com.bellyful.bellyfulapp.Model.AcceptedJobModel;
import com.bellyful.bellyfulapp.Model.FreezerModel;
import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.Model.MealModel;
import com.bellyful.bellyfulapp.Freezers.FreezerConfirmFragment;
import com.bellyful.bellyfulapp.Freezers.FreezersUpdateFragment;
import com.bellyful.bellyfulapp.Freezers.FreezersContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FreezersFragment.OnListFragmentInteractionListener, FreezersUpdateFragment.OnListFragmentInteractionListener, FreezerConfirmFragment.OnFragmentInteractionListener, JobSubmitFragment.OnJobSubmitFragmentInteractionListener, NewJobsFragment.OnDataPass {

    BottomNavigationView bottomNavigationView; // Bottom navigation bar
    private ArrayList<JobData> newJobList = new ArrayList<>(); // New jobs to pass to the NewJobFragment
    private ArrayList<FreezerModel> freezerList = new ArrayList<>(); // Freezer info to pass to the FreezerFragment
    protected ArrayList <AcceptedJobModel> selectedJobList = new ArrayList<>(); // Jobs for the CurrentJobFragment
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Initialize an instance of Firebase Auth
    private FirebaseDatabase database = FirebaseDatabase.getInstance(); // Initialize an instance of the database

    Toolbar mToolbar;
    Fragment ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Database listeners to fill the ArrayLists above
        setJobUpdateListener();
        setFreezerUpdateListen();
        setAcceptedJobUpdateListener();
        setCompletedJobUpdateListener();

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.new_deliveries);
        //Load starting fragment
        loadNewJobsFragment();

    }

    //Deals with bottom navigation bar interaction
    //Loads appropriate fragments on click
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_new_jobs:
                    loadNewJobsFragment();
                    return true;
                case R.id.action_current_jobs:
                    //Bundle contains jobs selected in the newJobFragment
                    Bundle currentJobsBundle = new Bundle();
                    currentJobsBundle.putParcelableArrayList("selectedJobList", selectedJobList);
                    ft = new CurrentJobsFragment();
                    ft.setArguments(currentJobsBundle);
                    loadFragment(ft);
                    return true;
                case R.id.action_freezers:
                    loadFreezerFragment();
                    return true;
                case R.id.action_job_submit:
                    ft = new JobSubmitFragment();
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

    //Generalised function to load fragments
    public void loadFragment(Fragment fragment) {
        //For making loading fragments easier and cleaner
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //Checks users are logged in then updates the UI accordingly
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
    public void onListFragmentInteraction(FreezersContent.FreezerItem item) {
        ft = new FreezersUpdateFragment();
        loadFragment(ft);
    }

    @Override
    public void onListFreezersUpdateFragmentInteraction(FreezersContent.FreezerItem item) {

    }

    @Override
    public void onJobSubmitFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Update listener for new jobs
    public void setJobUpdateListener(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //First clear the job list to avoid duplicates
                newJobList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Load each document into the ArrayList. The getValue parameter tells Firebase the class structure
                    newJobList.add(ds.getValue(JobData.class));
                }
                //This is the first fragment to load so it needs to be updated immediately
                loadNewJobsFragment();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("JobData", "Failed to read value.", error.toException());
            }
        };

        //Attach the listener to a database reference.
        //The listener is listening for a collection called "JobData"
        DatabaseReference listenerRef = database.getReference().child("JobData");
        listenerRef.addValueEventListener(valueEventListener);
    }

    //The rest of these listeners work exactly the same as above. Could possibly be refactored.
    public void setAcceptedJobUpdateListener(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                selectedJobList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds != null) {
                        selectedJobList.add(ds.getValue(AcceptedJobModel.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AcceptedJob", "Failed to read value.", error.toException());
            }
        };

        DatabaseReference listenerRef = database.getReference().child("AcceptedJob");
        listenerRef.addValueEventListener(valueEventListener);
    }

    public void setCompletedJobUpdateListener(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                selectedJobList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds != null) {
                        selectedJobList.add(ds.getValue(AcceptedJobModel.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CompletedJob", "Failed to read value.", error.toException());
            }
        };

        DatabaseReference listenerRef = database.getReference().child("CompletedJob");
        listenerRef.addValueEventListener(valueEventListener);
    }

    private void setFreezerUpdateListen() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                freezerList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    freezerList.add(ds.getValue(FreezerModel.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Freezer", "Failed to read value.", error.toException());
            }
        };

        DatabaseReference listenerRef = database.getReference().child("Freezer");
        listenerRef.addValueEventListener(valueEventListener);
    }

    //Function to load the NewJobsFragment
    //This is it's own function because it's used a couple of times
    public void loadNewJobsFragment(){
        //Send jobData to the fragment
        Bundle newJobsBundle = new Bundle();
        newJobsBundle.putParcelableArrayList("newJobList", newJobList);
        ft = new NewJobsFragment();
        ft.setArguments(newJobsBundle);
        loadFragment(ft);
    }

    //Same as above
    public void loadFreezerFragment(){
        Bundle freezerBundle = new Bundle();
        freezerBundle.putParcelableArrayList("freezerList", freezerList);
        ft = new FreezersFragment();
        ft.setArguments(freezerBundle);
        loadFragment(ft);
    }

    //Implements interface from NewJobsFragment.OnDataPass
    //Used to pass accepted jobs between the mainActivity and the NewJobFragment
    @Override
    public void onDataPass(AcceptedJobModel selectedItems) {
        selectedJobList.add(selectedItems);
    }


}
