package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.content.Context;
import android.util.Log;
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

import com.bellyful.bellyfulapp.CurrentJobsFragment;
import com.bellyful.bellyfulapp.Model.AcceptedJobModel;
import com.bellyful.bellyfulapp.Model.DatabaseHelper;
import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CurrentJobsRecyclerAdapter extends RecyclerView.Adapter<CurrentJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mCode; // 1 = Outstanding, 2 = Complete
    private ArrayList<AcceptedJobModel> mOutstandingJobs;
    private ArrayList<AcceptedJobModel> mCompletedJobs = new ArrayList<>();
    private FragmentManager mFragmentManager;
    EventBus bus = EventBus.getDefault();


    CurrentJobsRecyclerAdapter(Context context, int code, ArrayList<AcceptedJobModel> itemsList, FragmentManager fragmentManager) {
        //Load outstanding jobs
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCode = code;
        this.mFragmentManager = fragmentManager;
        if(code == 1) {
            this.mOutstandingJobs = itemsList;
        }else if(mCode == 2){
            this.mCompletedJobs = itemsList;
        }
    }

    @NonNull
    @Override
    public NewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        if(mCode == 0){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_confirmed), parent, false);
        }else if(mCode == 1){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_outstanding), parent, false);
        }else if(mCode == 2){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_completed), parent, false);
        }else{
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_confirmed), parent, false);
        }

        NewJobViewHolder vh = new NewJobViewHolder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewJobViewHolder viewHolder, final int position) {
        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.nameLabel.setText("");
        if(mCode == 1) {
            if (mOutstandingJobs.size() > 0) {
                final AcceptedJobModel currentItem = mOutstandingJobs.get(position);
//            ArrayList numMeals = new ArrayList();
                viewHolder.nameLabel.setText(currentItem.getName());
                viewHolder.addressLabel.setText(currentItem.getAddress());
                viewHolder.phoneLabel.setText(currentItem.getPhone());

//                //Count number of mMeals in the list and put them in a string
//                Map<String, Integer> numMeals = new HashMap<>();
//                for (int i = 0; i < currentItem.getMeals().size(); i++) {
//                    int occurrences = Collections.frequency(currentItem.getMeals(), currentItem.getMeals().get(i));
//                    numMeals.put(currentItem.getMeals().get(i), occurrences);
//                }
//                StringBuilder mealString = new StringBuilder();
//                for (String key : numMeals.keySet()) {
//                    mealString.append(key).append("x");
//                    mealString.append(numMeals.get(key).toString()).append(" ");
//                }
                StringBuilder mealString = new StringBuilder();
                for (String key : currentItem.getMeals().keySet()) {
                    mealString.append(key).append("x");
                    mealString.append(currentItem.getMeals().get(key).toString()).append(" ");
                }


                viewHolder.foodLabel.setText(mealString);
            }
        }else if(mCode == 2){
            if (mCompletedJobs.size() > 0) {
                final AcceptedJobModel currentItem = mCompletedJobs.get(position);
//            ArrayList numMeals = new ArrayList();
                viewHolder.nameLabel.setText(currentItem.getName());
                viewHolder.addressLabel.setText(currentItem.getAddress());
                viewHolder.phoneLabel.setText(currentItem.getPhone());

//                //Count number of mMeals in the list and put them in a string
//                Map<String, Integer> numMeals = new HashMap<>();
//                for (int i = 0; i < currentItem.getMeals().size(); i++) {
//                    int occurrences = Collections.frequency(currentItem.getMeals(), currentItem.getMeals().get(i));
//                    numMeals.put(currentItem.getMeals().get(i), occurrences);
//                }
//                StringBuilder mealString = new StringBuilder();
//                for (String key : numMeals.keySet()) {
//                    mealString.append(key).append("x");
//                    mealString.append(numMeals.get(key).toString()).append(" ");
//                }
                StringBuilder mealString = new StringBuilder();
                for (String key : currentItem.getMeals().keySet()) {
                    mealString.append(key).append("x");
                    mealString.append(currentItem.getMeals().get(key).toString()).append(" ");
                }


                viewHolder.foodLabel.setText(mealString);
            }else{
                viewHolder.foodLabel.setText("");
                viewHolder.addressLabel.setText("");
                viewHolder.phoneLabel.setText("");
                viewHolder.nameLabel.setText("");
            }
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
        if(mCode == 1) {
            return mOutstandingJobs.size();
        }else if (mCode == 2){
            return mCompletedJobs.size();
        }
        return 0;
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
            if(mCode == 1) {
                final Button dropDownButton = itemView.findViewById(R.id.btnOptions);

                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startButton.setVisibility(View.GONE);
                        completeButton.setVisibility(View.VISIBLE);
                        notifyDataSetChanged();
                    }
                });
                completeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AcceptedJobModel currentItem = mOutstandingJobs.get(getAdapterPosition());
                        String id = currentItem.getId();
                        DatabaseHelper.removeFromDbByID(currentItem, id);

                        //TODO: add completed jobs to db
                        mCompletedJobs.add(mOutstandingJobs.get(getAdapterPosition()));
                        mOutstandingJobs.remove(getAdapterPosition());


//                        for(int i = 0; i < mOutstandingJobs.size(); i++){
//                            AcceptedJobModel currentItem = mOutstandingJobs.get(i);
//                            mJobsTaken.add(currentItem);//Get a list of the selected jobs
////                                passData(mJobsTaken.get(i)); //
//                            String id = currentItem.getID();
//                            DatabaseHelper.removeFromDbByID(currentItem, id);
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            AcceptedJobModel acceptedJob = new AcceptedJobModel(currentItem.getID(), user, currentItem.getName(),
//                                    currentItem.getAddress(), currentItem.getPhone(), currentItem.getMeals());
//                            DatabaseHelper.addToDb(acceptedJob);
//                            mJobList.remove(mCurrentSelectedItems.get(i));
//                            Log.d("deletion", id + " was removed");
//                        }
////                            passData(mJobsTaken);
//                        createRecyclerView();


//                        bus.post(new CurrentJobsFragment.dataChangedEvent(mCompletedJobs));

                        notifyDataSetChanged();
                        startButton.setVisibility(View.VISIBLE);
                        completeButton.setVisibility(View.GONE);
                    }
                });

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
//                                        ft.replace(R.mID.frameContainer, nextFrag);
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
}
