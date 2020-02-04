package com.bellyful.bellyfulapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class JobData extends DatabaseHelper implements Parcelable {

//    public ArrayList<JobModel> mJobArray = new ArrayList<>();
//    public ArrayList<JobData> dummyJobs = new ArrayList<>();

    //    public JobData testData;
    public String id;
    public String name;
    public String address;
    public String phone;
    public ArrayList<String> meals = new ArrayList<>();

    public JobData(){
        super("JobData");
//        createTestData();
    }


    //TODO: Remove later. This constructor is for testing
    public JobData(int i){
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

    private JobData(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        in.readStringList(meals);
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
        parcel.writeStringList(meals);
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

    public ArrayList<String> getMeals() {
        return meals;
    }

    //USED FOR TEST DATA
    //TODO: DELETE THIS LATER
    //_______________________________________________________________
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
        //-----testing-----
        JobData testData = new JobData(1);
        for(int i = 0; i < 5; i++) {
            testData.id = Integer.toString(i);
            testData.name = JobData.DataGenerator.generateName(i);
            testData.address = JobData.DataGenerator.generateAddress(i);
            testData.phone = "021 " + i + " 22 33" + i;
//            testData.meals = JobData.DataGenerator.generateMeals(i);
            testData.meals.clear();
            switch (i) {
                case 0:
                    testData.meals.add("Las");
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    testData.meals.add("Bol");
                    break;
                case 1:
                    testData.meals.add("Las");
                    testData.meals.add("Las");
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    break;
                case 2:
                    testData.meals.add("Las");
                    testData.meals.add("Las");
                    testData.meals.add("Bol");
                    testData.meals.add("Bol");
                    break;
                case 3:
                    testData.meals.add("Bol");
                    testData.meals.add("Bol");
                    break;
                case 4:
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    break;
                case 5:
                    testData.meals.add("M&C");
                    testData.meals.add("M&C");
                    testData.meals.add("Bol");
                    break;
                default:
                    testData.meals.add("Name:");
                    break;
            }
//            newJobList.add(testData);
            DatabaseHelper.addToDb("JobData", testData);
        }
        //-----------
    }
}
