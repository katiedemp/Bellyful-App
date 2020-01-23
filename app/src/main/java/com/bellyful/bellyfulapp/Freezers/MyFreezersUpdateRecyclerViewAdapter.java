package com.bellyful.bellyfulapp.Freezers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bellyful.bellyfulapp.Freezers.FreezersUpdateFragment.OnListFragmentInteractionListener;
import com.bellyful.bellyfulapp.R;
import com.bellyful.bellyfulapp.Freezers.FreezerContent.FreezerItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FreezerItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFreezersUpdateRecyclerViewAdapter extends RecyclerView.Adapter<MyFreezersUpdateRecyclerViewAdapter.ViewHolder> {

    private final List<FreezerItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFreezersUpdateRecyclerViewAdapter(List<FreezerItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_freezersupdate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFreezersUpdateFragmentInteraction(holder.mItem);
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
        public TextView mMeal1View;
        public TextView mMeal2View;
        public TextView mMeal3View;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.freezer_meal);
            mNameView = view.findViewById(R.id.freezer_owner);
            mMeal1View = view.findViewById(R.id.freezer_meal1);
            mMeal2View = view.findViewById(R.id.freezer_meal2);
            mMeal3View = view.findViewById(R.id.freezer_meal3);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
