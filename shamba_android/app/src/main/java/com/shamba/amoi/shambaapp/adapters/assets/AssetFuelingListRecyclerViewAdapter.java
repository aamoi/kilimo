package com.shamba.amoi.shambaapp.adapters.assets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.models.assets.AssetFuelingItem;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.ProductStockItem;
import com.shamba.amoi.shambaapp.models.product.StockUtilizationItem;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class AssetFuelingListRecyclerViewAdapter extends
        RecyclerView.Adapter<AssetFuelingListRecyclerViewAdapter.ViewHolder> {

    private final List<StockUtilizationItem> mValues;
    private final HomeActivity homeActivity;

    public AssetFuelingListRecyclerViewAdapter(List<StockUtilizationItem> items,
                                               HomeActivity homeActivity) {
        mValues = items;
        this.homeActivity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_assetfuelinglist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.first_column.setText(mValues.get(position).getUtilized_date().substring(0,10));

        AssetItem assetItem= AssetItem.getAssetItemById(AssetItem.getAllAssets(homeActivity),
                mValues.get(position).getAsset_id());
        String product_name= ProductItem.getProductItemByID(ProductItem.getAllProducts(homeActivity),
                assetItem.getFuel_product_id()).getProduct_name();
        holder.second_column.setText(product_name);

        holder.third_column.setText(String.valueOf(mValues.get(position).getUtilized_quantity()));
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
        public StockUtilizationItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            first_column = (TextView) view.findViewById(R.id.txt_first_column);
            second_column = (TextView) view.findViewById(R.id.txt_second_column);
            third_column = (TextView) view.findViewById(R.id.txt_third_column);
        }

        @Override
        public String toString() {
            return super.toString() + " '" +
                    first_column.getText() + "'";
        }
    }
}
