package com.bellyful.bellyfulapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
//import com.bellyful.bellyfulapp.FreezersUI.FreezerConfirmFragment;
//import com.bellyful.bellyfulapp.FreezersUI.FreezersUpdateFragment;
import com.bellyful.bellyfulapp.Model.DatabaseHelper;
import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.Model.MealModel;
import com.bellyful.bellyfulapp.Freezers.FreezerConfirmFragment;
import com.bellyful.bellyfulapp.Freezers.FreezersUpdateFragment;
import com.bellyful.bellyfulapp.Freezers.FreezerContent;
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
    private ArrayList<JobData> newJobList = new ArrayList<>();
    protected ArrayList <JobData> selectedJobList = new ArrayList<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth
    private FirebaseDatabase database = FirebaseDatabase.getInstance(); //

    Toolbar mToolbar;
    Fragment ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setUpdateListener("Meal");
//        createDbEntries();
//        MealModel meal = new MealModel();
//        meal.retrieveFromDb();

        //Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        createTestData();
//        JobData testData = new JobData();
        setJobUpdateListener();

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.new_deliveries);
        //Load starting fragment
        loadNewJobsFragment();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_new_jobs:
                    loadNewJobsFragment();
                    return true;
                case R.id.action_current_jobs:
                    Bundle currentJobsBundle = new Bundle();
                    currentJobsBundle.putParcelableArrayList("selectedJobList", selectedJobList);
                    ft = new CurrentJobsFragment();
                    ft.setArguments(currentJobsBundle);
                    loadFragment(ft);
                    return true;
                case R.id.action_freezers:
                    ft = new FreezersFragment();
                    loadFragment(ft);
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
    public void onJobSubmitFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void createDbEntries(){
        MealModel meal = new MealModel();
        meal.setName("Test");
        meal.setId("3");
//        meal.addToDb(meal);
    }

    public void setJobUpdateListener(){
        //////////////////  Database update listener   //////////////////////////
//        final ArrayList<JobData> JobList = new ArrayList<>();
//        ArrayList list = new ArrayList();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newJobList.clear();
                int index = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    newJobList.add(ds.getValue(JobData.class));
//                    String id = ds.child(collectionType).getValue(String.class);
                    Log.d("JobData", newJobList.get(index).getName());
                    ++index;
                }
                loadNewJobsFragment();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("JobData", "Failed to read value.", error.toException());
            }
        };

        DatabaseReference listenerRef = database.getReference().child("JobData");
        listenerRef.addValueEventListener(valueEventListener);

        /////////////////////////////////////////////////////////

    }

    public void loadNewJobsFragment(){
        //Send jobData to the fragment
        Bundle newJobsBundle = new Bundle();
        newJobsBundle.putParcelableArrayList("newJobList", newJobList);
        ft = new NewJobsFragment();
        ft.setArguments(newJobsBundle);
        loadFragment(ft);
    }

    @Override
    public void onDataPass(JobData selectedItems) {
        selectedJobList.add(selectedItems);
    }


}
