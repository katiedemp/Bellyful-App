package com.bellyful.bellyfulapp;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bellyful.bellyfulapp.Model.AcceptedJobModel;
import com.bellyful.bellyfulapp.Model.DatabaseHelper;
import com.bellyful.bellyfulapp.Model.JobData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewJobsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewJobsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "newJobList";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<JobData> mCurrentSelectedItems = new ArrayList<>();//Keeps a list of selected items
    private ArrayList<JobData> mJobsTaken = new ArrayList<>();
    private ArrayList<JobData> mJobList = new ArrayList<>(); //List of new jobs

    public NewJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewJobsFragment newInstance(String param1, String param2) {
        NewJobsFragment fragment = new NewJobsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mJobList = args.getParcelableArrayList("newJobList");
        }

        //Init NewJobRecycler
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_new_jobs, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.NewJobRecycler);
        createRecyclerView();

        //Set the Toolbar
        Toolbar toolbar = ((AppCompatActivity)getActivity()).findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.new_deliveries);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button acceptJobButton = view.findViewById(R.id.btnAcceptJobs);
        //OnClickListener for the accept button
        acceptJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //"Are you sure?" popup dialogue
            new AlertDialog.Builder(getActivity())
                    .setTitle("Confirm Delivery")
                    .setMessage("Are you sure you want to take this delivery?")
                    //Specifying a listener allows you to take an action before dismissing the dialog.
                    //The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        //Accept Job
                        public void onClick(DialogInterface dialog, int which) {
                            //Loop through all the ticked items and move them into the CurrentJob collection
                            for(int i = 0; i < mCurrentSelectedItems.size(); i++){
                                JobData currentItem = mCurrentSelectedItems.get(i);
                                String id = currentItem.getID();
                                DatabaseHelper.removeFromDbByID(currentItem, id);
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                String userID = user.getUid();
                                AcceptedJobModel acceptedJob = new AcceptedJobModel();
                                acceptedJob.fillObject(currentItem.getID(), userID, "outstanding", currentItem.getName(),
                                        currentItem.getAddress(), currentItem.getPhone(), currentItem.getMeals());
                                DatabaseHelper.addToDb(acceptedJob);
                                mJobList.remove(mCurrentSelectedItems.get(i));
                                Log.d("deletion", id + " was removed");
                            }
                            createRecyclerView();
                        }
                    })
                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    OnDataPass dataPasser;
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
        dataPasser = (OnDataPass) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();

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

    private void createRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //The mJobList is a parameter of the adapter to pass the list to the recycler
        mRecyclerAdapter = new NewJobsRecyclerAdapter(getActivity(), mJobList, new NewJobsRecyclerAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(JobData item) {
                mCurrentSelectedItems.add(item);
            }

            @Override
            public void onItemUncheck(JobData item) {
                mCurrentSelectedItems.remove(item);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    //Interface to pass data to the main activity
    public interface OnDataPass{
        void onDataPass(AcceptedJobModel selectedItems);
    }

    //Part of the interface
    public void passData(AcceptedJobModel data) {
        dataPasser.onDataPass(data);
    }

}
