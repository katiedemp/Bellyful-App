package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.CurrentJobsFragment;
import com.bellyful.bellyfulapp.Model.CompletedJobModel;
import com.bellyful.bellyfulapp.Model.JobData;
import com.bellyful.bellyfulapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class CompletedJobTab extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CompletedJobModel> mCompleteItems = new ArrayList<>();
    private EventBus bus = EventBus.getDefault(); //Used to pass data between the Outstanding tab


    public CompletedJobTab(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_completed_job, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myCompletedRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CompletedJobsRecyclerAdapter(getActivity(), mCompleteItems, getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }

    private void drawRecyclerAdapter(){

    }

    //catch Event from fragment A
    public void onEvent(CurrentJobsFragment.dataChangedEvent event) {
        mCompleteItems = event.mNewData;
        mRecyclerAdapter = new CompletedJobsRecyclerAdapter(getActivity(), mCompleteItems, getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        mRecyclerAdapter = new CompletedJobsRecyclerAdapter(getActivity(), mCompleteItems, getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }




}
