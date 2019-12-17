package com.bellyful.bellyfulapp;

import com.bellyful.bellyfulapp.Model.JobModel;

import java.util.ArrayList;

public class TestData {

    public ArrayList<JobModel> mJobArray = new ArrayList<>();

    public TestData(int num){
        createJobs(num);
    }

    public void createJobs(int num){
        for(int i = 0; i < num; i++){
            JobModel newJob = new JobModel();
            newJob.setId(Integer.toString(i));
            newJob.setClientId(Integer.toString(i));
            newJob.setReferralId("1");
            newJob.setFreezerId("1");
            newJob.setMealId("1");
            newJob.setNumMealsTaken(4);
            newJob.setStatus("order placed");
            newJob.setJobInfo("None");
            mJobArray.add(newJob);
        }
    }

    public JobModel getJob(int id){
        return mJobArray.get(id);
    }

    public String generateName(int index){
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

    public String generateAddress(int index){
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

    public String generateMeals(int index) {
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

//    public void run(int numJobs){
//        createJobs(numJobs);
//
//    }
}
