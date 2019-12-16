package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.CurrentJobsFragment;
import com.bellyful.bellyfulapp.NewJobsRecyclerAdapter;
import com.bellyful.bellyfulapp.R;

public class OutstandingJobTab extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_outstanding_job, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myOutstandingRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }



}
