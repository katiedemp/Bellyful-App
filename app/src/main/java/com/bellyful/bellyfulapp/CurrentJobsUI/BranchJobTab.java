package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.Model.AcceptedJobModel;
import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.R;

import java.util.ArrayList;

public class BranchJobTab extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AcceptedJobModel> mSelectedItems = new ArrayList<>();
    private Bundle args;


    public BranchJobTab(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        args = getArguments();
        if(args != null) {
            mSelectedItems = args.getParcelableArrayList("selectedJobList");
        }else{
            mSelectedItems.add(new AcceptedJobModel(-1));
        }

        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_branch_job, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.branchOutstandingRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity(), 2, mSelectedItems, getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }
}
