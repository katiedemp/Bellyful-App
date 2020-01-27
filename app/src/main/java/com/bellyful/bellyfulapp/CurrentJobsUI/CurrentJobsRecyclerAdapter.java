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

import com.bellyful.bellyfulapp.JobData;
import com.bellyful.bellyfulapp.R;

import java.util.ArrayList;

public class CurrentJobsRecyclerAdapter extends RecyclerView.Adapter<CurrentJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mCode;
    private ArrayList<JobData> mOutstandingJobs;
    private FragmentManager mFragmentManager;


    CurrentJobsRecyclerAdapter(Context context, int code, ArrayList<JobData> selectedItemsList, FragmentManager fragmentManager) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCode = code;
        this.mOutstandingJobs = selectedItemsList;
        this.mFragmentManager = fragmentManager;
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
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_outstanding), parent, false);
        }else{
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_confirmed), parent, false);
        }

        NewJobViewHolder vh = new NewJobViewHolder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewJobViewHolder viewHolder, int position) {
        //TODO: Set to null first
        viewHolder.foodLabel.setText("");
        viewHolder.addressLabel.setText("");
        viewHolder.phoneLabel.setText("");
        viewHolder.nameLabel.setText("");
        if(mOutstandingJobs.size() > 0) {
            final JobData currentItem = mOutstandingJobs.get(position);
            viewHolder.nameLabel.setText(currentItem.name);
            viewHolder.addressLabel.setText(currentItem.address);
            viewHolder.phoneLabel.setText(currentItem.phone);
            viewHolder.foodLabel.setText(currentItem.meals);
        }
    }

    @Override
    public int getItemCount() {
        return mOutstandingJobs.size();
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

            //Dropdown button for outstanding jobs
            if(mCode == 1) {
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
}
