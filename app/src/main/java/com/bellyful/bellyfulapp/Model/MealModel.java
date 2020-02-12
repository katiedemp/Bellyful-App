package com.bellyful.bellyfulapp.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MealModel extends DatabaseHelper{

    private String id;
    private String name;

    private final String TAG = "MealModel";

    //Constructor parameter tells the Database Helper which collection to use
    public MealModel() {
        super("Meal");
//        final ArrayList<MealModel> MealList = new ArrayList<>();
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                mID = dataSnapshot.child("mID").getValue().toString();
////                mName = dataSnapshot.child("mName").getValue().toString();
////                DatabaseHelper value = dataSnapshot.getValue(DatabaseHelper.class);
////                Log.d(TAG, "Value is: " + value);
//                int index = 0;
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    MealList.add(ds.getValue(MealModel.class));
////                    MealList.get(index);
//                    Log.d(TAG, MealList.get(index).getName());
//                    ++index;
//                }
////                Log.d(TAG, mealUpdate.getName());
////                Log.d(TAG, "Value is: " + mealUpdate);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        };
//        DatabaseReference listenerRef = database.getReference().child(collectionType);
//        listenerRef.addListenerForSingleValueEvent(valueEventListener);
    }

//    @Override
//    public void retrieveFromDb(){
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                mID = dataSnapshot.child("mID").getValue().toString();
////                mName = dataSnapshot.child("mName").getValue().toString();
////                DatabaseHelper value = dataSnapshot.getValue(DatabaseHelper.class);
////                Log.d(TAG, "Value is: " + value);
//                MealModel mealUpdate = dataSnapshot.getValue(MealModel.class);
//                Log.d(TAG, "Value updated");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }

    // ----- Getters and Setters -----
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //-------------------------------
}
