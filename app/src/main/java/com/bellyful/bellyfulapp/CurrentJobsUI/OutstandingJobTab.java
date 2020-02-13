package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.Model.AcceptedJobModel;
import com.bellyful.bellyfulapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class OutstandingJobTab extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AcceptedJobModel> mAllSelectedItems = new ArrayList<>();
    private ArrayList<AcceptedJobModel> mUserSelectedItems = new ArrayList<>();

    public OutstandingJobTab(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        if(args != null) {
            mAllSelectedItems = args.getParcelableArrayList("selectedJobList");
        }

        if(!mAllSelectedItems.isEmpty()){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userID = user.getUid();
            for(int i = 0; i < mAllSelectedItems.size(); i++) {
                if(mAllSelectedItems.get(i).getUser().equals(userID)){
                    mUserSelectedItems.add(mAllSelectedItems.get(i));
                }
            }
        }

        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_outstanding_job, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myOutstandingRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity(), 1, mAllSelectedItems, getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }



}
