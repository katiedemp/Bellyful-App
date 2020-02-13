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
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*
    Abstract class to provide interaction with the realtime database.
    Each model that extends this class represents a collection in the db.

    If variable members are added or removed from children of this class you need to clear previous entries of
    that collection from the database, otherwise they won't be uploaded/downloaded properly.
 */
public abstract class DatabaseHelper{

    //Exclude tag stops these variables from being stored in the DB
    @Exclude
    protected String collectionType;
    @Exclude
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Exclude
    protected DatabaseReference ref;

    private String TAG = "DatabaseHelper";


    DatabaseHelper(){
        //Default constructor required for  calls to DataSnapshot.getValue()
    }

    //Constructor to determine the collection type.
    DatabaseHelper(String _collectionType) {
        collectionType = _collectionType;
        ref = database.getReference().child(collectionType);
    }

    /*
       Polymorphic function. You can pass any instance of a class that extends DatabaseHelper
       as a parameter and it should add it to the Database.
    * */
    public static void addToDb(final DatabaseHelper newDocument) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference().child(newDocument.collectionType);
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

    //Similar to the addToDB function. Will remove a document based on the given id
    public static void removeFromDbByID(DatabaseHelper document, String id){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference().child(document.collectionType);
        Query query = dbRef.orderByChild("id").equalTo(id);
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

    @Exclude
    public String getCollectionType() {
        return collectionType;
    }
    @Exclude
    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }
}
