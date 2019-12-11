package com.bellyful.bellyfulapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentJobsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentJobsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pageNum";
    private static final String ARG_PARAM2 = "pageTitle";

    private int mPageNum;
    private String mTitle;

    private OnFragmentInteractionListener mListener;

    public CurrentJobsFragment() {
        // Required empty public constructor
    }

//    public CurrentJobsFragment(int page, String title) {
//        CurrentJobsFragment currentJobsFragment = new CurrentJobsFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        currentJobsFragment.setArguments(args);
//        return currentJobsFragment;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @param title Parameter 2.
     * @return A new instance of fragment CurrentJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentJobsFragment newInstance(int page, String title) {
        CurrentJobsFragment fragment = new CurrentJobsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageNum = getArguments().getInt(ARG_PARAM1);
            mTitle = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_jobs, container, false);
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.currentJobsViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_jobs, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = null;
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    //This sets up the viewPager controlling the top tabs
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ViewPager viewPager = view.findViewById(R.id.currentJobsViewPager);
//        TabLayout tabLayout = view.findViewById(R.id.currentJobTabLayout);
//
//        //Attach tabLayout to viewPager
//        tabLayout.setupWithViewPager(viewPager);
//
//        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getChildFragmentManager());
//
//
//    }
}
