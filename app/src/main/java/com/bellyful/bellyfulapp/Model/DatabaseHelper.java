package com.bellyful.bellyfulapp.Model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bellyful.bellyfulapp.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
    Abstract class to provide interaction with the realtime database.
    Each model that extends this class represents a collection in the db
 */
public abstract class DatabaseHelper extends AppCompatActivity {
    private String collectionType;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected DatabaseReference ref;

    private String TAG = "DatabaseHelper";


    DatabaseHelper(){
        //Default constructor required for  calls to DataSnapshot.getValue()
    }

    DatabaseHelper(String _collectionType) {
        collectionType = _collectionType;
        database.getReference().child(_collectionType);

        // Read/Update from the database
        //TODO: Test this works when there's data in db
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
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

    public void addToDb(final DatabaseHelper newDocument) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference().child(collectionType);
        ref.push().setValue(newDocument)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(DatabaseHelper.this, collectionType + " added to db",
                        Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DatabaseHelper.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO: Make abstract after some testing
//    public abstract void retrieveFromDb();
    public void retrieveFromDb(){}

    public void hydrateObject(final DatabaseHelper document){
        document.retrieveFromDb();
    }

//
//    public void removeFromDb(DatabaseHelper document){
//        database.collection("cities").document("DC")
//                .delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error deleting document", e);
//                    }
//                });
//    }
}
