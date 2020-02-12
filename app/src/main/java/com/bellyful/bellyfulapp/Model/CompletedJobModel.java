package com.bellyful.bellyfulapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletedJobModel extends DatabaseHelper implements Parcelable {

    private String id;
    private String user;
    private String name;
    private String address;
    private String phone;
    public Map<String, Integer> meals = new HashMap<>();

    public CompletedJobModel(){
        super("CompletedJob");
//        createTestData();
    }

    public void fillObject(String id, String user, String name, String address, String phone, Map<String, Integer> meals){
        this.id = id;
        this.user = user;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.meals = meals;
    }

    private CompletedJobModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    public static final Creator<CompletedJobModel> CREATOR = new Creator<CompletedJobModel>() {
        @Override
        public CompletedJobModel createFromParcel(Parcel in) {
            return new CompletedJobModel(in);
        }

        @Override
        public CompletedJobModel[] newArray(int size) {
            return new CompletedJobModel[size];
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

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String mUserID) {
        this.user = mUserID;
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

}
