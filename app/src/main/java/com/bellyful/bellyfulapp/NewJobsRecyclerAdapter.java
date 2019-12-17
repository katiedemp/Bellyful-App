package com.bellyful.bellyfulapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewJobsRecyclerAdapter extends RecyclerView.Adapter<NewJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
//    private ArrayList<JobModel> mJobArray;
    private ArrayList<JobData> mDummyData;
//    @NonNull
//    private OnItemCheckListener onItemCheckListener;
    private OnItemCheckListener onItemClick;




    NewJobsRecyclerAdapter(Context context, ArrayList dummyArray, @NonNull OnItemCheckListener onItemCheckListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDummyData = dummyArray;
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
//        JobData testData = new JobData(0); //For creating junk data
        final JobData currentItem = mDummyData.get(position);
        if (viewHolder instanceof NewJobViewHolder) {
            viewHolder.jobCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    viewHolder.jobCheckBox.setChecked(!viewHolder.jobCheckBox.isChecked()); /
                    if (viewHolder.jobCheckBox.isChecked()) {
                        onItemClick.onItemCheck(currentItem);
                    } else {
                        onItemClick.onItemUncheck(currentItem);
                    }
                }
            });
        }

//        JobData currentItem = mDummyData.get(position);

        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.foodLabel.setText("");

//        viewHolder.nameLabel.setText(testData.generateName(position));
//        viewHolder.addressLabel.setText(testData.generateAddress(position));
//        viewHolder.phoneLabel.setText("021 " + position + " 22 33" + position);
//        viewHolder.foodLabel.setText(testData.generateMeals(position));
        viewHolder.nameLabel.setText(currentItem.name);
        viewHolder.addressLabel.setText(currentItem.address);
        viewHolder.phoneLabel.setText(currentItem.phone);
        viewHolder.foodLabel.setText(currentItem.meals);

    }

    @Override
    public int getItemCount() {
        return mDummyData.size();
    }

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
            jobCheckBox.setClickable(false);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }
}
