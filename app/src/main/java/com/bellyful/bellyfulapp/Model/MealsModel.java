package com.bellyful.bellyfulapp.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MealsModel extends DatabaseHelper {

    private String id;
    private String name;
    private int quantity;

    public MealsModel(String collection) {
        super(collection);
    }

    @Override
    public void addToDb(DatabaseHelper newDocument) {
        ref.push().setValue(newDocument);
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //-------------------------------
}
