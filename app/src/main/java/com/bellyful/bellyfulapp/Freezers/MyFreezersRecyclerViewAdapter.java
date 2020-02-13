package com.bellyful.bellyfulapp.Freezers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bellyful.bellyfulapp.FreezersFragment.OnListFragmentInteractionListener;
import com.bellyful.bellyfulapp.Model.FreezerModel;
import com.bellyful.bellyfulapp.R;
import com.bellyful.bellyfulapp.Freezers.FreezersContent.FreezerItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FreezerItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFreezersRecyclerViewAdapter extends RecyclerView.Adapter<MyFreezersRecyclerViewAdapter.ViewHolder> {

    private final List<FreezerModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFreezersRecyclerViewAdapter(List<FreezerModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_freezers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).id);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mAddressView.setText(mValues.get(position).address);
        holder.mPhoneView.setText(mValues.get(position).phone);

        //Get strings from meals HashMap
        ArrayList<String> meal = new ArrayList<>();
        ArrayList<Integer> qty = new ArrayList<>();
        for(String key : mValues.get(position).meals.keySet()){
            meal.add(key);
            qty.add(mValues.get(position).meals.get(key));
        }

        holder.mMeal1View.setText(meal.get(0));
        holder.mMeal2View.setText(meal.get(1));
        holder.mMeal3View.setText(meal.get(2));
        holder.mMealQty1View.setText(Integer.toString(qty.get(0)));
        holder.mMealQty2View.setText(Integer.toString(qty.get(1)));
        holder.mMealQty3View.setText(Integer.toString(qty.get(2)));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public FreezerItem mItem;
        public TextView mNameView;
        public TextView mAddressView;
        public TextView mPhoneView;
        public TextView mMeal1View;
        public TextView mMeal2View;
        public TextView mMeal3View;
        public TextView mMealQty1View;
        public TextView mMealQty2View;
        public TextView mMealQty3View;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.freezer_details);
            mContentView = view.findViewById(R.id.freezer_stock);
            mNameView = view.findViewById(R.id.freezer_name);
            mAddressView = view.findViewById(R.id.freezer_address);
            mPhoneView = view.findViewById(R.id.freezer_phone);
            mMeal1View = view.findViewById(R.id.freezer_meal1);
            mMeal2View = view.findViewById(R.id.freezer_meal2);
            mMeal3View = view.findViewById(R.id.freezer_meal3);
            mMealQty1View = view.findViewById(R.id.freezer_mealQty1);
            mMealQty2View = view.findViewById(R.id.freezer_mealQty2);
            mMealQty3View = view.findViewById(R.id.freezer_mealQty3);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
