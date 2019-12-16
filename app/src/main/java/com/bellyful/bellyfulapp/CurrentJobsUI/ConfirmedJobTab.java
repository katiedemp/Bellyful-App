package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bellyful.bellyfulapp.R;

public class ConfirmedJobTab extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public ConfirmedJobTab(){
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_confirmed_job, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myConfirmedRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }

    //Inflate top tab pages after the parent fragment is created
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

    }
}
