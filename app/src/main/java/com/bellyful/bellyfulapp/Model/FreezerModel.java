package com.bellyful.bellyfulapp.Model;

class FreezerModel extends DatabaseHelper {

    private String id;
    private String volunteerId;
    private String mealsAvailableId;

    public String name;
    public String address;
    public String phone;
    public String mealName;
    public String mealQty;

    public FreezerModel() {
        super("Freezer");
    }

    public FreezerModel(String name, String address, String phone, String mealName, String mealQty) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mealName = mealName;
        this.mealQty = mealQty;
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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }

    public String getMealQty() { return mealQty; }
    public void setMealQty(String mealQty) { this.mealQty = mealQty; }



}

