package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CompletedJobsRecyclerAdapter extends RecyclerView.Adapter<CompletedJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mCode; // 1 = Outstanding, 2 = Complete
    private ArrayList<JobData> mCompletedJobs;
    private FragmentManager mFragmentManager;


    CompletedJobsRecyclerAdapter(Context context, ArrayList<JobData> itemsList, FragmentManager fragmentManager) {
        //Load outstanding jobs
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mCompletedJobs = itemsList;
    }

    @NonNull
    @Override
    public NewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_completed), parent, false);
        NewJobViewHolder vh = new NewJobViewHolder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewJobViewHolder viewHolder, final int position) {
        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.nameLabel.setText("");
        if (mCompletedJobs.size() > 0) {
            final JobData currentItem = mCompletedJobs.get(position);
//            ArrayList numMeals = new ArrayList();
            viewHolder.nameLabel.setText(currentItem.name);
            viewHolder.addressLabel.setText(currentItem.address);
            viewHolder.phoneLabel.setText(currentItem.phone);

            //Count number of meals in the list and put them in a string
            Map<String, Integer> numMeals = new HashMap<>();
            for (int i = 0; i < currentItem.meals.size(); i++) {
                int occurrences = Collections.frequency(currentItem.meals, currentItem.meals.get(i));
                numMeals.put(currentItem.meals.get(i), occurrences);
            }
            StringBuilder mealString = new StringBuilder();
            for (String key : numMeals.keySet()) {
                mealString.append(key).append("x");
                mealString.append(numMeals.get(key).toString()).append(" ");
            }

            viewHolder.foodLabel.setText(mealString);
        }else{
            viewHolder.foodLabel.setText("");
            viewHolder.addressLabel.setText("");
            viewHolder.phoneLabel.setText("");
            viewHolder.nameLabel.setText("");
        }

//        if(mCode == 1){
//            viewHolder.startButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    viewHolder.startButton.setVisibility(View.GONE);
//                    viewHolder.completeButton.setVisibility(View.VISIBLE);
//                }
//            });
//            viewHolder.completeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mOutstandingJobs.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
            return mCompletedJobs.size();
    }

    class NewJobViewHolder extends RecyclerView.ViewHolder{

        TextView nameLabel;
        TextView addressLabel;
        TextView phoneLabel;
        TextView foodLabel;
        Button startButton;
        Button completeButton;

        public NewJobViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.lblJobName);
            addressLabel = itemView.findViewById(R.id.lblJobAddress);
            phoneLabel = itemView.findViewById(R.id.lblJobPhone);
            foodLabel = itemView.findViewById(R.id.lblJobFood);
            startButton = itemView.findViewById(R.id.btnStart);
            completeButton = itemView.findViewById(R.id.btnComplete);

            //Dropdown button for outstanding jobs
            final Button dropDownButton = itemView.findViewById(R.id.btnOptions);

            dropDownButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(mContext, dropDownButton);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.job_popup_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menuItemCall:
                                    //handle menu1 click
                                    return true;
                                case R.id.menuItemText:
                                    //handle menu2 click
                                    return true;
                                case R.id.moreOptions:
//                                        OutstandingJobActivity nextFrag= new OutstandingJobActivity();
//                                        FragmentTransaction ft = mFragmentManager.beginTransaction();
//                                        ft.replace(R.id.frameContainer, nextFrag);
//                                        ft.addToBackStack(null);
//                                        ft.commit();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    //displaying the popup
                    popup.show();

                }
            });
        }
    }
}
