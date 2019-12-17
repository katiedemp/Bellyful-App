package com.bellyful.bellyfulapp.Model;

public class JobModel extends DatabaseHelper{

    private String id;
    private String volunteerId;
    private String clientId;
    private String referralId;
    private String freezerId;
    private String mealId;
    private int numMealsTaken;
    private String status;
    private String jobInfo;
    private String deliveryStartTime;
    private String deliveryEndTime;


    public JobModel() {
        super("Job");
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        //TODO: Check this exists before setting
        this.clientId = clientId;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        //TODO: Check this exists before setting
        this.referralId = referralId;
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

    public void setMealId(String mealId) {
        //TODO: Check this exists before setting
        this.mealId = mealId;
    }

    public int getNumMealsTaken() {
        return numMealsTaken;
    }

    public void setNumMealsTaken(int numMealsTaken) {
        this.numMealsTaken = numMealsTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(String deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public String getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(String deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }
}
