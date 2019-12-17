package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.MainActivity;
import com.bellyful.bellyfulapp.R;

public class CurrentJobsRecyclerAdapter extends RecyclerView.Adapter<CurrentJobsRecyclerAdapter.NewJobViewHolder>  {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mCode;
    private FragmentManager mFragmentManager;


    CurrentJobsRecyclerAdapter(Context context, int code, FragmentManager fragmentManager) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCode = code;
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
    public void onBindViewHolder(@NonNull NewJobViewHolder holder, int position) {
        //TODO: Set to null first
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class NewJobViewHolder extends RecyclerView.ViewHolder{

        public NewJobViewHolder(@NonNull View itemView) {
            super(itemView);

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
