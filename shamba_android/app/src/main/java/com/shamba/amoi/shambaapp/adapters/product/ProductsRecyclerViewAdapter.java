package com.shamba.amoi.shambaapp.adapters.product;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.product.ProductStockFragment;
import com.shamba.amoi.shambaapp.models.product.ProductCategoryItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsRecyclerViewAdapter
        extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> implements Filterable {
    private final List<ProductItem> product_list;
    private final HomeActivity homeActivity;
    public Bundle bundle;
    String product_name;
    String product_type;
    private List<ProductItem> productItems;
    private List<ProductItem> productListFiltered;
    List<ProductItem> allProductItems = new ArrayList<>();
    private Context context;

    public ProductsRecyclerViewAdapter(List<ProductItem> items, HomeActivity homeActivity) {
        product_list = items;
        this.homeActivity = homeActivity;
    }

    public void setProductList(Context context, final List<ProductItem> productItems) {
        this.context = context;
        if (this.productItems == null) {
            this.productItems = productItems;

            this.productListFiltered = productItems;
            notifyItemChanged(0, productListFiltered.size());
        } else {
            allProductItems = productItems;

            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProductsRecyclerViewAdapter.this.productItems.size();
                }

                @Override
                public int getNewListSize() {
                    return productItems.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProductsRecyclerViewAdapter.this.productItems.get(oldItemPosition).
                            getProduct_name() == productItems.get(newItemPosition).getProduct_name();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    ProductItem newProduct = ProductsRecyclerViewAdapter.this.productItems.get(oldItemPosition);

                    ProductItem oldProduct = productItems.get(newItemPosition);

                    return newProduct.getProduct_name() == oldProduct.getProduct_name();
                }
            });
            this.productItems = productItems;
            this.productListFiltered = productItems;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    productListFiltered = ProductItem.getAllProducts(homeActivity);

                } else {
                    List<ProductItem> filteredList = new ArrayList<>();
                    for (ProductItem productItem : ProductItem.getAllProducts(homeActivity)) {
                        if (productItem.getProduct_name().toLowerCase().
                                contains(charString.toLowerCase())) {
                            filteredList.add(productItem);
                            Log.d("Search ", productItem.getProduct_name());
                        }
                    }
                    productListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = productListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productListFiltered = (List<ProductItem>) filterResults.values;
                productItems.clear();
                productItems.addAll(productListFiltered);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.productItem = product_list.get(position);
        product_name = product_list.get(position).getProduct_name();
        holder.product_name.setText(product_name);

        int product_category = product_list.get(position).getCategory_id();

        product_type = ProductCategoryItem.getProductCategoryItemByID(
                ProductCategoryItem.staticProductCategoryItemList,
                product_list.get(position).getCategory_id()).getCategory_name();
        holder.product_type.setText(product_type);
    }

    @Override
    public int getItemCount() {
        Log.d("Check", String.valueOf(product_list.size()));
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
                    ProductItem.selectedProductItem = productItem;
                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);

                        builder.setTitle(Html.fromHtml(
                                "<font color='#FF7000'>Select action on " +
                                        product_name.getText().toString() + "</font>"));

                        builder.setNegativeButton(Html.fromHtml(
                                "<font color='#FF7000'>Stock</font>"),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,
                                                new ProductStockFragment());
                                    }
                                });

                        builder.setPositiveButton(Html.fromHtml(
                                "<font color='#FF7000'>Details</font>")
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
    }
}
