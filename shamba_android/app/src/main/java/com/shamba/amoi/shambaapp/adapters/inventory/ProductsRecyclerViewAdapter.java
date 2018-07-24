package com.shamba.amoi.shambaapp.adapters.inventory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.product.ProductStockFragment;
import com.shamba.amoi.shambaapp.models.product.ProductCategoryItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.List;

/**
 */

public class ProductsRecyclerViewAdapter
        extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> {
    private final List<ProductItem> product_list;
//    private final OnListFragmentInteractionListener itemListener;
    private final HomeActivity homeActivity;
    public Bundle bundle;
    String product_name;
    String product_type;

    public ProductsRecyclerViewAdapter(List<ProductItem> items, HomeActivity homeActivity) {
        product_list = items;
//        itemListener = listener;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inventory, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.productItem = product_list.get(position);
        product_name=product_list.get(position).getProduct_name();
        holder.product_name.setText(product_name);

        int product_category=product_list.get(position).getCategory_id();

        product_type= ProductCategoryItem.getProductCategoryItemByID(
                ProductCategoryItem.staticProductCategoryItemList,
                product_list.get(position).getCategory_id()).getCategory_name();

        holder.product_type.setText(product_type);
    }
    @Override
    public int getItemCount() {
        Log.d("Check", String.valueOf(product_list.size()) );

        return product_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView product_name;
        public final TextView product_type;
        public ProductItem productItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            product_name = (TextView) view.findViewById(R.id.product_name);
            product_type = (TextView) view.findViewById(R.id.product_type);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductItem.selectedProductItem=productItem;
                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);

                        builder.setTitle(Html.fromHtml(
                                "<font color='#FF7000'>Select action on "+
                                        product_name.getText().toString()+"</font>"));

                        builder.setNegativeButton(Html.fromHtml("<font color='#FF7000'>Stock</font>"),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        BaseFragment.changeFragment((AppCompatActivity) homeActivity,
                                                R.id.fragment_placeholder_home,new ProductStockFragment());

                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,new ProductStockFragment());
                                    }
                                });

                        builder.setPositiveButton( Html.fromHtml("<font color='#FF7000'>Details</font>")
                                , new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } catch (Exception ex)

                    {
                        ex.printStackTrace();
                    }
                }
            };

            mView.setOnClickListener(listener);
            product_name.setOnClickListener(listener);
            product_type.setOnClickListener(listener);
        }
}}
