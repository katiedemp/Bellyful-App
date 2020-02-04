package com.bellyful.bellyfulapp.Model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bellyful.bellyfulapp.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*
    Abstract class to provide interaction with the realtime database.
    Each model that extends this class represents a collection in the db
 */
public abstract class DatabaseHelper{
    protected String collectionType;
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected DatabaseReference ref;

    private String TAG = "DatabaseHelper";


    DatabaseHelper(){
        //Default constructor required for  calls to DataSnapshot.getValue()
    }

    DatabaseHelper(String _collectionType) {
        collectionType = _collectionType;
//        database.getReference().child(_collectionType);
        ref = database.getReference().child(collectionType);

        // Read/Update from the database
        //TODO: Test this works when there's data in db
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                DatabaseHelper value = dataSnapshot.getValue(DatabaseHelper.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    public static void addToDb(String collectionType, final DatabaseHelper newDocument) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference().child(collectionType);
        dbRef.push().setValue(newDocument)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Toast.makeText(DatabaseHelper.this, collectionType + " added to db",
//                        Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(DatabaseHelper.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void removeFromDbByID(String collectionType, String id){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference().child(collectionType);
//        String query = dbRef.child("id").child(id).getKey();
        Query query = dbRef.orderByChild("id").equalTo(id);
//        query.getRef().removeValue();
//        dbRef.child("id").child(id).removeValue();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ds.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DB Error", "onCancelled", databaseError.toException());
            }
        });
    }
}
