package com.bellyful.bellyfulapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_TITLE = "pageTitle";

    private int mPageNum;
    private String mTitle;

    private OnFragmentInteractionListener mListener;

    public CurrentJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @param title Parameter 2.
     * @return A new instance of fragment CurrentJobsFragment.
     */
    static CurrentJobsFragment newInstance(int page, String title) {
        CurrentJobsFragment fragment = new CurrentJobsFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_NUM, page);
        args.putString(PAGE_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageNum = getArguments().getInt(PAGE_NUM);
            mTitle = getArguments().getString(PAGE_TITLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_jobs, container, false);
    }

    //Inflate top tab pages after the parent fragment is created
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

        //Inflate ViewPager tabs
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.currentJobsViewPager);
        TabLayout tabLayout = getView().findViewById(R.id.currentJobTabLayout);
        CurrentJobViewPagerAdapter viewPagerAdapter = new CurrentJobViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(new ConfirmedJobTab(), "Confirmed Jobs");
        viewPagerAdapter.addFragment(new OutstandingJobTab(), "Outstanding Jobs");
        viewPagerAdapter.addFragment(new BranchJobTab(), "Branch Outstanding Jobs");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
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

}
