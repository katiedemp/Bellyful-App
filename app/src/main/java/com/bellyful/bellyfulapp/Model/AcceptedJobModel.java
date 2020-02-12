package com.bellyful.bellyfulapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcceptedJobModel extends DatabaseHelper implements Parcelable {

//    public ArrayList<JobModel> mJobArray = new ArrayList<>();
//    public ArrayList<JobData> dummyJobs = new ArrayList<>();

    //    public JobData testData;
    private String id;
    private String user;
    private String name;
    private String address;
    private String phone;
    public Map<String, Integer> meals = new HashMap<>();

    public AcceptedJobModel(){
        super("AcceptedJob");
//        createTestData();
    }

    public void fillObject(String id, String user, String name, String address, String phone, Map<String, Integer> meals){
        this.id = id;
        this.user = user;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.meals = meals;

//        createTestData();
    }


    //TODO: Remove later. This constructor is for testing
    public AcceptedJobModel(int i){
        if(i >= 0) {
//            this.id = i;
//            this.name = DataGenerator.generateName(i);
//            this.address = DataGenerator.generateAddress(i);
//            this.phone = "021 " + i + " 22 33" + i;
//            this.meals.add("Las");
//            this.meals.add("M&C");
//            this.meals.add("Bol");
//            this.meals.add(DataGenerator.generateMeals(i));
//        }else{
//            this.name = "No Outstanding Deliveries";
//            this.address = "";
//            this.phone = "";
//            this.meals.add("");
        }
    }
//
//    public JobData(int num){
////        createJobs(num);
//        createDummyData(num);
//    }

//    public void createDummyData(int i){
//        JobData testData = new JobData();
//        testData.name = DataGenerator.generateName(i);
//        testData.address = DataGenerator.generateAddress(i);
//        testData.phone = "021 " + i + " 22 33" + i;
//        testData.meals = DataGenerator.generateMeals(i);
//    }

//    public void createJobs(int num){
////        for(int i = 0; i < num; i++){
////            JobModel newJob = new JobModel();
////            newJob.setId(Integer.toString(i));
////            newJob.setClientId(Integer.toString(i));
////            newJob.setReferralId("1");
////            newJob.setFreezerId("1");
////            newJob.setMealId("1");
////            newJob.setNumMealsTaken(4);
////            newJob.setStatus("order placed");
////            newJob.setJobInfo("None");
////            mJobArray.add(newJob);
////        }
////    }

    private AcceptedJobModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
//        in.readStringList(meals);
    }

    public static final Creator<AcceptedJobModel> CREATOR = new Creator<AcceptedJobModel>() {
        @Override
        public AcceptedJobModel createFromParcel(Parcel in) {
            return new AcceptedJobModel(in);
        }

        @Override
        public AcceptedJobModel[] newArray(int size) {
            return new AcceptedJobModel[size];
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
