package com.bellyful.bellyfulapp.Model;

public class MealModel extends DatabaseHelper{

    private String id;
    private String name;

    //Constructor parameter tells the Database Helper which collection to use
    public MealModel() {
        super("Meal");
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

    //-------------------------------
}
