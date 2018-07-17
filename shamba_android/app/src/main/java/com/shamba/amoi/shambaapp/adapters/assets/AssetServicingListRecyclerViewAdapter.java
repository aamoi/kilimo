package com.shamba.amoi.shambaapp.adapters.assets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.assets.AssetServicingListFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AssetServicingListRecyclerViewAdapter
        extends RecyclerView.Adapter<AssetServicingListRecyclerViewAdapter.ViewHolder> {

    private final List<AssetServicingItem> mValues;
    private final HomeActivity homeActivity;

    public AssetServicingListRecyclerViewAdapter(List<AssetServicingItem> items,
                                                 HomeActivity homeActivity) {
        mValues = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_assetservicinglist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.first_column.setText(mValues.get(position).getAssert_id());
        holder.second_column.setText(mValues.get(position).getAssert_id());
        holder.third_column.setText(mValues.get(position).getAssert_id());
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

        public AssetServicingItem mItem;

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
