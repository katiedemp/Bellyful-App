package com.bellyful.bellyfulapp.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class DatabaseHelper {

    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected DatabaseReference ref;


    public DatabaseHelper(String collection) {
        ref = database.getReference().child(collection);
    }

    public abstract void addToDb(DatabaseHelper newDocument);

}
