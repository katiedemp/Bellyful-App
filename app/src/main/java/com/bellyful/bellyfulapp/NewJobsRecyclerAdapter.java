package com.bellyful.bellyfulapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.Model.JobModel;

import java.util.ArrayList;

public class NewJobsRecyclerAdapter extends RecyclerView.Adapter<NewJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<JobModel> mJobArray;


    NewJobsRecyclerAdapter(Context context, ArrayList<JobModel> jobArray) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mJobArray = jobArray;
    }

    @NonNull
    @Override
    public NewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_new_jobs), parent, false);
        NewJobViewHolder vh = new NewJobViewHolder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewJobViewHolder viewHolder, int position) {
        TestData testData = new TestData(0); //For creating junk data

        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.foodLabel.setText("");

        viewHolder.nameLabel.setText(testData.generateName(position));
        viewHolder.addressLabel.setText(testData.generateAddress(position));
        viewHolder.phoneLabel.setText("021 " + position + " 22 33" + position);
        viewHolder.foodLabel.setText(testData.generateMeals(position));

    }

    @Override
    public int getItemCount() {
        return mJobArray.size();
    }

    class NewJobViewHolder extends RecyclerView.ViewHolder{

        TextView nameLabel;
        TextView addressLabel;
        TextView phoneLabel;
        TextView foodLabel;

        public NewJobViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.lblJobName);
            addressLabel = itemView.findViewById(R.id.lblJobAddress);
            phoneLabel = itemView.findViewById(R.id.lblJobPhone);
            foodLabel = itemView.findViewById(R.id.lblJobFood);

        }
    }
}
