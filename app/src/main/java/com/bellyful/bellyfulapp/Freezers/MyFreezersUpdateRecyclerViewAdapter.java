package com.bellyful.bellyfulapp.Freezers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bellyful.bellyfulapp.Freezers.FreezersUpdateFragment.OnListFragmentInteractionListener;
import com.bellyful.bellyfulapp.R;
import com.bellyful.bellyfulapp.Freezers.FreezersContent.FreezerItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FreezerItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFreezersUpdateRecyclerViewAdapter extends RecyclerView.Adapter<MyFreezersUpdateRecyclerViewAdapter.ViewHolder> {

    private final List<FreezerItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int quantity = 0;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mMeal1View.setText(mValues.get(position).meal1);
        holder.mMealQty1View.setText(mValues.get(position).mealQty1);

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

        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.mQuantityView.getText()));
                quantity = count;
                quantity++;
                holder.mQuantityView.setText(String.valueOf(quantity));
            }

        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.mQuantityView.getText()));
                quantity = count;
                quantity--;
                holder.mQuantityView.setText(String.valueOf(quantity));
            }

        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyClickListener listener;
        public final View mView;
        public final TextView mIdView;
        public FreezerItem mItem;
        public TextView mMeal1View;
        public TextView mMealQty1View;
        TextView mQuantityView;
        Button incrementButton;
        Button decrementButton;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mMeal1View = view.findViewById(R.id.freezer_meal_name);
            mMealQty1View = view.findViewById(R.id.freezer_stock_qty);
            incrementButton = view.findViewById(R.id.increment_button);
            decrementButton = view.findViewById(R.id.decrement_button);
            mQuantityView = view.findViewById(R.id.quantity_textview);

            incrementButton.setOnClickListener(this);
            decrementButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.increment_button:
                    listener.onIncrement(this.getLayoutPosition());
                    break;
                case R.id.decrement_button:
                    listener.onDecrement(this.getLayoutPosition());
                    break;
                default:
                    break;
            }
        }
        public interface MyClickListener {
            void onIncrement(int p);
            void onDecrement(int p);
        }

    }
}
