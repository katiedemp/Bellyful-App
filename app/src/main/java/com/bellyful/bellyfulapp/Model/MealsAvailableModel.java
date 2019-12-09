package com.bellyful.bellyfulapp.Model;

public class MealsAvailableModel extends DatabaseHelper {

    private String freezerId;
    private String mealId;
    private int quantity;

    public MealsAvailableModel() {
        super("MealsAvailable");
    }

    public String getFreezerId() {
        return freezerId;
    }

    public void setFreezerId(String freezerId) {
        //TODO: Check this exists before setting
        this.freezerId = freezerId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealsId) {
        //TODO: Check this exists before setting
        this.mealId = mealsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
