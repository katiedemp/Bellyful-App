package com.bellyful.bellyfulapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.Model.JobData;

import java.util.ArrayList;

public class NewJobsRecyclerAdapter extends RecyclerView.Adapter<NewJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<JobData> jobData;
//    @NonNull
//    private OnItemCheckListener onItemCheckListener;
    private OnItemCheckListener onItemClick;



    //Constructor takes an ArrayList of job data as a parameter to fill the recycler
    NewJobsRecyclerAdapter(Context context, ArrayList<JobData> jobData, @NonNull OnItemCheckListener onItemCheckListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.jobData = jobData;
        this.onItemClick = onItemCheckListener;
    }

    interface OnItemCheckListener {
        void onItemCheck(JobData item);
        void onItemUncheck(JobData item);
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
    public void onBindViewHolder(@NonNull final NewJobViewHolder viewHolder, int position) {
        final JobData currentItem = jobData.get(position);

        if (viewHolder instanceof NewJobViewHolder) {
            //Set checkbox listeners
            viewHolder.jobCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.jobCheckBox.isChecked()) {
                        onItemClick.onItemCheck(currentItem);
                    } else {
                        onItemClick.onItemUncheck(currentItem);
                    }
                }
            });
        }

        //Clear the textViews to avoid left over text
        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.foodLabel.setText("");

        //Fill textViews
        viewHolder.nameLabel.setText(currentItem.getName());
        viewHolder.addressLabel.setText(currentItem.getAddress());
        viewHolder.phoneLabel.setText(currentItem.getPhone());

        //Turn the meals HashMap into a string for the textView
        StringBuilder mealString = new StringBuilder();
        for (String key : currentItem.getMeals().keySet()) {
            mealString.append(key).append("x");
            mealString.append(currentItem.getMeals().get(key).toString()).append(" ");
        }

        viewHolder.foodLabel.setText(mealString);

    }

    @Override
    public int getItemCount() {
        return jobData.size();
    }

    //Standard viewHolder
    class NewJobViewHolder extends RecyclerView.ViewHolder{

        TextView nameLabel;
        TextView addressLabel;
        TextView phoneLabel;
        TextView foodLabel;

        CheckBox jobCheckBox;

        public NewJobViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.lblJobName);
            addressLabel = itemView.findViewById(R.id.lblJobAddress);
            phoneLabel = itemView.findViewById(R.id.lblJobPhone);
            foodLabel = itemView.findViewById(R.id.lblJobFood);
            jobCheckBox = itemView.findViewById(R.id.checkBoxJob);
            jobCheckBox.setClickable(false); //Each checkbox is assigned an individual OnclickListener in the adapter instead
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

    }
}
