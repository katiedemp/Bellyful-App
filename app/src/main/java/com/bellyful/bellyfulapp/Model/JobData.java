package com.bellyful.bellyfulapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.bellyful.bellyfulapp.Model.FreezerModel.generateMealQty;
import static com.bellyful.bellyfulapp.Model.FreezerModel.generateMeals;

public class JobData extends DatabaseHelper implements Parcelable {

    //These member variables become data fields in the DB
    private String id;
    private String name;
    private String address;
    private String phone;
    public Map<String, Integer> meals = new HashMap<>();

    //Empty constructor required for Firebase
    public JobData(){
        super("JobData");
//        createTestData();
    }

    public JobData(String collectionType){
        super(collectionType);
    }


    public JobData(int i){
        //Use for testing/errors
    }

    // -------------- The following is required to make JobData parcelable -------------
    private JobData(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    public static final Parcelable.Creator<JobData> CREATOR = new Parcelable.Creator<JobData>() {
        @Override
        public JobData createFromParcel(Parcel in) {
            return new JobData(in);
        }

        @Override
        public JobData[] newArray(int size) {
            return new JobData[size];
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
    //------------------------End of parcelable functions---------------------------------------------

    //Getters and setters
    public String getID() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMeals(Map<String, Integer> meals) {
        this.meals = meals;
    }


    //--------------- USED FOR CREATING TEST DATA (this can be removed at any time)------------------
    static class DataGenerator{
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
                default:
                    return "Address:";
            }
        }

        public static String generateMeals(int index) {
            switch (index) {
                case 0:
                    return "Las x1, M&C x2, Bol x1";
                case 1:
                    return "Las x2, M&C x2";
                case 2:
                    return "Las x2, Bol x2";
                case 3:
                    return "Bol x3";
                case 4:
                    return "M&C x4";
                case 5:
                    return "M&C x2, Bol x1";
                default:
                    return "Name:";
            }
        }
    }

    public void createTestData(){
        for(int i = 0; i < 5; i++) {
            this.id = Integer.toString(i);
            this.name = JobData.DataGenerator.generateName(i);
            this.address = JobData.DataGenerator.generateAddress(i);
            this.phone = "021 " + i + " 22 33" + i;
            for(int index = 0; index < 3; index++) {
                this.meals.put(generateMeals(i), generateMealQty(i));
            }
            DatabaseHelper.addToDb(this);
        }
    }
    //--------------------END OF TESTING FUNCTIONS-----------------------------------
}
