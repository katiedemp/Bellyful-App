package com.bellyful.bellyfulapp;

import android.os.Parcel;
import android.os.Parcelable;

public class JobData implements Parcelable {

//    public ArrayList<JobModel> mJobArray = new ArrayList<>();
//    public ArrayList<JobData> dummyJobs = new ArrayList<>();

//    public JobData testData;
    public String name;
    public String address;
    public String phone;
    public String meals;



    public JobData(int i){
       this.name = DataGenerator.generateName(i);
        this.address = DataGenerator.generateAddress(i);
        this.phone = "021 " + i + " 22 33" + i;
        this.meals = DataGenerator.generateMeals(i);
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

    protected JobData(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        meals = in.readString();
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
        parcel.writeString(meals);
    }

//    public JobModel getJob(int id){
//        return mJobArray.get(id);
//    }

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
//    public void run(int numJobs){
//        createJobs(numJobs);
//
//    }
}
