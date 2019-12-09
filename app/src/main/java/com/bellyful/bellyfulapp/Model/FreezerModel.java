package com.bellyful.bellyfulapp.Model;

class FreezerModel extends DatabaseHelper {

    private String id;
    private String volunteerId;
    private String mealsAvailableId;

    public FreezerModel() {
        super("Freezer");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        //TODO: Check this exists before setting
        this.volunteerId = volunteerId;
    }

    public String getMealsAvailableId() {
        return mealsAvailableId;
    }

    public void setMealsAvailableId(String mealsAvailableId) {
        //TODO: Check this exists before setting
        this.mealsAvailableId = mealsAvailableId;
    }
}

