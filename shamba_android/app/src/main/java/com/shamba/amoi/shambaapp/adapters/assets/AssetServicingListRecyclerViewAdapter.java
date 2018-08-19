package com.shamba.amoi.shambaapp.adapters.assets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.assets.AssetFuelingListFragment;
import com.shamba.amoi.shambaapp.fragments.assets.AssetServicingListFragment;
import com.shamba.amoi.shambaapp.fragments.assets.AssetServicingListFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.fragments.assets.CreateServiceResourceAssignmentFragment;
import com.shamba.amoi.shambaapp.fragments.labor.PaymentsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.models.assets.ServiceTypeItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

public class AssetServicingListRecyclerViewAdapter
        extends RecyclerView.Adapter<AssetServicingListRecyclerViewAdapter.ViewHolder> {

    private final List<AssetServicingItem> mValues;
    private final HomeActivity homeActivity;

    public AssetServicingListRecyclerViewAdapter(List<AssetServicingItem> items,
                                                 HomeActivity homeActivity) {
        mValues = items;
        this.homeActivity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_assetservicinglist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.assetServicingItem = mValues.get(position);
        holder.date.setText(mValues.get(position).getPlanned_service_start_date().
                substring(0, 10));
        String service_type = ServiceTypeItem.getServiceTypeById(homeActivity, mValues.get(position).
                getService_type_id()).getName();
        holder.amount.setText(String.valueOf(mValues.get(position).getService_cost()));
        holder.serviceType.setText(service_type);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView date;
        public final TextView amount;
        public final TextView serviceType;

        public AssetServicingItem assetServicingItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            date = (TextView) view.findViewById(R.id.txt_first_column);
            amount = (TextView) view.findViewById(R.id.txt_second_column);
            serviceType = (TextView) view.findViewById(R.id.txt_third_column);

            DialogUtility dialogUtility = new DialogUtility(homeActivity,
                    "Select action on service: ",
                    "Assign", "Complete", "Details") {

                @Override
                public void onSelectNegativeDialogueOption() {
                    AssetServicingItem.selectedAssetServicingItem = assetServicingItem;
                    BaseFragment.changeFragment(homeActivity, R.id.fragment_placeholder_home,
                            new AssetServicingListFragment());
                }

                @Override
                public void onSelectPostiveDialogueOption() {
                    AssetServicingItem.selectedAssetServicingItem = assetServicingItem;
                    BaseFragment.changeFragment(homeActivity, R.id.fragment_placeholder_home,
                            new PaymentsFragment());
                }

                @Override
                public void onSelectNeutralDialogueOption() {
                    AssetServicingItem.selectedAssetServicingItem = assetServicingItem;
                    BaseFragment.changeFragment(homeActivity, R.id.fragment_placeholder_home,
                            new CreateServiceResourceAssignmentFragment());
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(mView, date, amount, serviceType);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + date.getText() + "'";
        }
    }
}
