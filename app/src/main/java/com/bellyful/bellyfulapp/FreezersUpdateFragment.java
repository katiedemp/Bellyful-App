package com.bellyful.bellyfulapp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bellyful.bellyfulapp.dummy.DummyContent;
import com.bellyful.bellyfulapp.dummy.DummyContent.DummyItem;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FreezersUpdateFragment extends Fragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FreezersUpdateFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FreezersUpdateFragment newInstance(int columnCount) {
        FreezersUpdateFragment fragment = new FreezersUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freezersupdate_list, container, false);

        //Set buttons
        Button mealsTaken = view.findViewById(R.id.taken_button);
        Button mealsAdded = view.findViewById(R.id.added_button);
        mealsTaken.setOnClickListener(this);
        mealsAdded.setOnClickListener(this);

        //Set the Toolbar
        Toolbar toolbar = ((AppCompatActivity)getActivity()).findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.freezers_stock);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.update_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new MyFreezersUpdateRecyclerViewAdapter(DummyContent.ITEMS, mListener));

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment;
        switch (view.getId()) {
            case R.id.taken_button:
            case R.id.added_button:
                fragment = new FreezerConfirmFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFreezersUpdateFragmentInteraction(DummyItem item);
    }
}
