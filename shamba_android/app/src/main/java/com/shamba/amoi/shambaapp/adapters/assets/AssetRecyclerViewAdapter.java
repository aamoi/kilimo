package com.shamba.amoi.shambaapp.adapters.assets;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.assets.AssetFuelingListFragment;
import com.shamba.amoi.shambaapp.fragments.assets.AssetServicingListFragment;
import com.shamba.amoi.shambaapp.fragments.labor.PaymentsFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.UnitOfMeasureItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

public class AssetRecyclerViewAdapter extends
        RecyclerView.Adapter<AssetRecyclerViewAdapter.ViewHolder> {

    private final List<AssetItem> asset_items;
    private final HomeActivity activity;


    public AssetRecyclerViewAdapter(HomeActivity homeActivity, List<AssetItem> items) {
        asset_items = items;
        activity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_asset, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.assetItem = asset_items.get(position);
        holder.name.setText(this.asset_items.get(position).getName());

        String capacity = String.valueOf(this.asset_items.get(position).
                getCapacity_quantity());
        String capacity_uom = UnitOfMeasureItem.getUnitOfMeasureItemByID(UnitOfMeasureItem.
                staticUnitOfMeasureItemList, this.asset_items.get(position).getCapacity_uom_id())
                .getSymbol();
        holder.capacity.setText(capacity + " " + capacity_uom);

        String active_status = this.asset_items.get(position).getIs_active().equalsIgnoreCase(
                "TRUE") ? "Active " : "Inactive";
        holder.active_status.setText(active_status);
    }

    @Override
    public int getItemCount() {

        int size = asset_items != null ? this.asset_items.size() : 0;
        if (asset_items != null)
            Log.d("itemCount", String.valueOf(size));
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView capacity;
        public final TextView active_status;

        public AssetItem assetItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.txt_first_column);
            capacity = (TextView) view.findViewById(R.id.txt_second_column);
            active_status = (TextView) view.findViewById(R.id.txt_third_column);

            DialogUtility dialogUtility = new DialogUtility(activity,
                    "Select action on asset:", "Fuel",
                    "Service", "Details") {

                @Override
                public void onSelectNegativeDialogueOption() {
                    AssetItem.currentAssetItem = assetItem;
                    BaseFragment.changeFragment(activity, R.id.fragment_placeholder_home,
                            new AssetServicingListFragment());
                }
                @Override
                public void onSelectPostiveDialogueOption() {
                    AssetItem.currentAssetItem = assetItem;
                    BaseFragment.changeFragment(activity, R.id.fragment_placeholder_home,
                            new PaymentsFragment());
                }
                @Override
                public void onSelectNeutralDialogueOption() {
                    AssetItem.currentAssetItem = assetItem;
                    ProductItem.selectedProductItem = ProductItem.getProductItemByID(
                            ProductItem.getAllProducts(activity),assetItem.getFuel_product_id());

                    BaseFragment.changeFragment(activity, R.id.fragment_placeholder_home,
                            new AssetFuelingListFragment());
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(mView, name, capacity,active_status);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
