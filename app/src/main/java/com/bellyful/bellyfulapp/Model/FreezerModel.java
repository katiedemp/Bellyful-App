package com.bellyful.bellyfulapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FreezerModel extends DatabaseHelper implements Parcelable {

    public String id;
    public String name;
    public String address;
    public String phone;
//    public ArrayList<String> meals = new ArrayList<>();
    public Map<String, Integer> meals = new HashMap<>();

    public FreezerModel(){
        super("Freezer");
//        createData();
    }

    public FreezerModel(int i) {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Map<String, Integer> getMeals() {
        return meals;
    }

    //TODO: REMOVE. Test stuff
    public static String generateName(int index){
        switch (index) {
            case 0:
                return "Suzanne";
            case 1:
                return "George";
            case 2:
                return "Mary";
            case 3:
                return "Ashley";
            case 4:
                return "John";
            case 5:
                return "Lee";
            case 6:
                return "Steve";
            default:
                return "Name:";
        }
    }

    public static String generateAddress(int index){
        switch (index) {
            case 0:
                return "123 West St";
            case 1:
                return "2A Beach Rd";
            case 2:
                return "Unit 3a, 22 High Pd";
            case 3:
                return "32 Oscar Pl";
            case 4:
                return "21 Koru Dr";
            case 5:
                return "13B Bays Cr";
            case 6:
                return "24 North St";
            default:
                return "Address:";
        }
    }

    public static String generateMeals(int index) {
        switch (index) {
            case 0:
                return "Las";
            case 1:
                return "M&C";
            case 2:
                return "Bol";
            case 3:
                return "Veg Soup";
            case 4:
                return "Chick Soup";
            case 5:
                return "Veg Las";
            case 6:
                return "Curry";
            default:
                return "Name:";
        }
    }

    public static int generateMealQty(int index) {
        switch (index) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                return 0;
        }
    }

    public void createData(){
        FreezerModel testdata = new FreezerModel(1);
        for(int i = 0; i < 3; ++i){
            testdata.id = Integer.toString(i);
            testdata.name = generateName(i);
            testdata.address = generateAddress(i);
            testdata.phone = "021 " + i + " 22 33" + i;
            for(int index = 0; index < 3; index++) {
                testdata.meals.put(generateMeals(i), generateMealQty(i));
            }

            DatabaseHelper.addToDb(testdata);
        }
    }

    private FreezerModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
//        in.readStringList(meals);
    }

    public static final Parcelable.Creator<FreezerModel> CREATOR = new Parcelable.Creator<FreezerModel>() {
        @Override
        public FreezerModel createFromParcel(Parcel in) {
            return new FreezerModel(in);
        }

        @Override
        public FreezerModel[] newArray(int size) {
            return new FreezerModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeMap(meals);
    }
}
