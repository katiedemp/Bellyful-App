package com.bellyful.bellyfulapp.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
    Abstract class to provide interaction with the realtime database.
    Each model that extends this class represents a collection in the db
 */
public abstract class DatabaseHelper {
    private String collectionType;


    public DatabaseHelper(String _collectionType) {
        collectionType = _collectionType;
    }

//    public abstract void addToDb(DatabaseHelper newDocument);
    public void addToDb(DatabaseHelper newDocument) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(collectionType);
        ref.push().setValue(newDocument);
    }
}
