package com.shamba.amoi.shambaapp.adapters.power;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.models.power.PowerSourceStockItem;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class PowerSourceStockListRecyclerViewAdapter extends
        RecyclerView.Adapter<PowerSourceStockListRecyclerViewAdapter.ViewHolder> {

    private final List<PowerSourceStockItem> mValues;
    private final HomeActivity homeActivity;

    public PowerSourceStockListRecyclerViewAdapter(HomeActivity homeActivity,
                                                   List<PowerSourceStockItem> items) {
        mValues = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_powersourcestocklist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.first_column.setText(mValues.get(position).getVendor_name());
        holder.second_column.setText(mValues.get(position).getStock_date());
        holder.third_column.setText(String.valueOf(mValues.get(position).getStock_quantity()));


//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView first_column;
        public final TextView second_column;
        public final TextView third_column;

        public PowerSourceStockItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            first_column = (TextView) view.findViewById(R.id.txt_first_column);
            second_column = (TextView) view.findViewById(R.id.txt_second_column);
            third_column = (TextView) view.findViewById(R.id.txt_third_column);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + first_column.getText() + "'";
        }
    }
}
